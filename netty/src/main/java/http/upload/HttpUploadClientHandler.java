package http.upload;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpUploadClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    private boolean readingChunks;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            System.err.println("STATUS: " + response.getStatus());
            System.err.println("VERSION: " + response.getProtocolVersion());

            if (!response.headers().isEmpty()) {
                response.headers().names().forEach(name -> response.headers().getAll(name).forEach(value -> System.err.println("HEADER: " + name + " = " + value)));
            }

            if (response.getStatus().code() == 200 && HttpHeaders.isTransferEncodingChunked(response)) {
                readingChunks = true;
                System.err.println("CHUNKED CONTENT {");
            } else {
                System.err.println("CONTENT {");
            }
        }

        if (msg instanceof HttpContent) {
            HttpContent chunk = (HttpContent) msg;
            System.err.println(chunk.content().toString(CharsetUtil.UTF_8));

            if (chunk instanceof LastHttpContent) {
                if (readingChunks) {
                    System.err.println("} END OF CHUNKED CONTENT");
                } else {
                    System.err.println("} END OF CONTENT");
                }
                readingChunks = false;
            } else {
                System.err.println(chunk.content().toString(CharsetUtil.UTF_8));
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
