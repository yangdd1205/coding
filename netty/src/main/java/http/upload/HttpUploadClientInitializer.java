package http.upload;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.Objects;

public class HttpUploadClientInitializer extends ChannelInitializer<SocketChannel>{
    private final SslContext sslCtx;

    public HttpUploadClientInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (Objects.nonNull(sslCtx)) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast("codec", new HttpClientCodec());
        // Remove the following line if you don't want automatic content decompression.
        pipeline.addLast("inflater", new HttpContentDecompressor());

        // to be used since huge file transfer
        pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());

        pipeline.addLast("handler", new HttpUploadClientHandler());
    }
}
