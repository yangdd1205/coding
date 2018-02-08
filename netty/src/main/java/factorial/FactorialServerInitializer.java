package factorial;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.ssl.SslContext;

import java.util.Objects;


/**
 * @author yang.dd
 * @date 2018/2/8
 */
public class FactorialServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;

    public FactorialServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (Objects.nonNull(sslCtx)) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast(ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
        pipeline.addLast(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));
        // Add the number codec first,
        pipeline.addLast(new BigIntegerDecoder());
        pipeline.addLast(new NumberEncoder());
        pipeline.addLast(new FactorialServerHandler());
    }
}
