package http.upload;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.multipart.*;
import io.netty.handler.ssl.SslContext;
import io.netty.util.internal.SocketUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class HttpUploadClient {
    static final String BASE_URL = System.getProperty("baseUrl", "http://127.0.0.1:8080/");
    static final String FILE = System.getProperty("file", "upload.txt");

    public static void main(String[] args) throws Exception {
        String postSimple, postFile, get;
        if (BASE_URL.endsWith("/")) {
            postSimple = BASE_URL + "formpost";
            postFile = BASE_URL + "formpostmultipart";
            get = BASE_URL + "formget";
        } else {
            postSimple = BASE_URL + "/formpost";
            postFile = BASE_URL + "/formpostmultipart";
            get = BASE_URL + "/formget";
        }
        URI uriSimple = new URI(postSimple);
        String scheme = uriSimple.getScheme() == null ? "http" : uriSimple.getScheme();
        String host = uriSimple.getHost() == null ? "127.0.0.1" : uriSimple.getHost();
        int port = uriSimple.getPort();
        if (port == -1) {
            if ("http".equalsIgnoreCase(scheme)) {
                port = 80;
            } else if ("https".equalsIgnoreCase(scheme)) {
                port = 443;
            }
        }

        if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
            System.err.println("Only HTTP(S) is supported.");
            return;
        }

        final boolean ssl = "https".equalsIgnoreCase(scheme);
        final SslContext sslCtx = null;

        URI uriFile = new URI(postFile);
        File file = new File(FILE);
        if (!file.canRead()) {
            throw new FileNotFoundException(FILE);
        }

        EventLoopGroup group = new NioEventLoopGroup();
        // setup the factory: here using a mixed memory/disk based on size threshold
        // Disk if MINSIZE exceed
        HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);
        // should delete file on exit (in normal exit)
        DiskFileUpload.deleteOnExitTemporaryFile = true;
        // system temp directory
        DiskFileUpload.baseDirectory = null;
        // should delete file on exit (in normal exit)
        DiskAttribute.deleteOnExitTemporaryFile = true;
        // system temp directory
        DiskAttribute.baseDirectory = null;

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HttpUploadClientInitializer(sslCtx));
            List<Map.Entry<String, String>> headers = formget(b, host, port, get, uriSimple);
            if (headers == null) {
                factory.cleanAllHttpDatas();
                return;
            }
            // Simple Post form: factory used for big attributes
            List<InterfaceHttpData> bodylist = formpost(b, host, port, uriSimple, file, factory, headers);
            if (bodylist == null) {
                factory.cleanAllHttpDatas();
                return;
            }
            // Multipart Post form: factory used
            formpostmultipart(b, host, port, uriFile, factory, headers, bodylist);
        } finally {
            group.shutdownGracefully();
            factory.cleanAllHttpDatas();
        }
    }

    private static void formpostmultipart(Bootstrap b, String host, int port, URI uriFile, HttpDataFactory factory, List<Map.Entry<String, String>> headers, List<InterfaceHttpData> bodylist) {
    }

    private static List<InterfaceHttpData> formpost(Bootstrap bootstrap, String host, int port, URI uriSimple, File file, HttpDataFactory factory, List<Map.Entry<String, String>> headers) throws Exception {
        ChannelFuture future = bootstrap.connect(SocketUtils.socketAddress(host, port));
        // Wait until the connection attempt succeeds or fails.
        Channel channel = future.sync().channel();

        // Prepare the HTTP request.
        HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uriSimple.toASCIIString());

        HttpPostRequestEncoder bodyRequestEncoder =
                new HttpPostRequestEncoder(factory, request, false);  // false => not multipart

        return null;
    }

    private static List<Map.Entry<String, String>> formget(Bootstrap bootstrap, String host, int port, String get, URI uriSimple) throws Exception {
        Channel channel = bootstrap.connect(host, port).sync().channel();
        QueryStringEncoder encoder = new QueryStringEncoder(get);

        encoder.addParam("getform", "GET");
        encoder.addParam("info", "first value");
        encoder.addParam("secondinfo", "secondvalue ���&");
        encoder.addParam("thirdinfo", "third value\r\ntest second line\r\n\r\nnew line\r\n");
        encoder.addParam("Send", "Send");

        URI uriGet = new URI(encoder.toString());
        HttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uriGet.toASCIIString());
        HttpHeaders headers = request.headers();
        headers.set(HttpHeaders.Names.HOST, host);
        headers.set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
        headers.set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP + "," + HttpHeaders.Values.DEFLATE);

        headers.set(HttpHeaders.Names.ACCEPT_CHARSET, "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        headers.set(HttpHeaders.Names.ACCEPT_LANGUAGE,"zh-CN,zh;q=0.9");
        headers.set(HttpHeaders.Names.REFERER, uriSimple.toString());
        headers.set(HttpHeaders.Names.USER_AGENT, "Netty Simple Http Client side");
        headers.set(HttpHeaders.Names.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        headers.set(
                HttpHeaders.Names.COOKIE, ClientCookieEncoder.LAX.encode(
                        new DefaultCookie("my-cookie", "foo"),
                        new DefaultCookie("another-cookie", "bar"))
        );
        List<Map.Entry<String, String>> entries = headers.entries();
        channel.writeAndFlush(request);

        // Wait for the server to close the connection.
        channel.closeFuture().sync();

        return entries;
    }
}
