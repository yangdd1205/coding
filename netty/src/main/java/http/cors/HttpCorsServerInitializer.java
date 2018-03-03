package http.cors;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cors.CorsConfig;
import io.netty.handler.codec.http.cors.CorsHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;


public class HttpCorsServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public HttpCorsServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //允许任何来源
        //CorsConfig corsConfig = CorsConfig.withAnyOrigin().build();
        //指定来源
        //CorsConfig corsConfig = CorsConfig.withOrigin("http://eureka1:1205").build();

        //指定headers 只能比设置的少， 如果不设置则不能有任何的header
        CorsConfig corsConfig = CorsConfig.withAnyOrigin().allowedRequestHeaders("custom-request-header").build();
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new CorsHandler(corsConfig));
        pipeline.addLast(new OkResponseHandler());
    }
}
