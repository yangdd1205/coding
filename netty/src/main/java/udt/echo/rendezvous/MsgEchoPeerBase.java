package udt.echo.rendezvous;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.nio.NioUdtProvider;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;

public class MsgEchoPeerBase {
    protected final int messageSize;
    protected final InetSocketAddress self;
    protected final InetSocketAddress peer;

    protected MsgEchoPeerBase(final InetSocketAddress self, final InetSocketAddress peer, final int messageSize) {
        this.messageSize = messageSize;
        this.self = self;
        this.peer = peer;
    }


    public void run() throws Exception {
        final ThreadFactory connectFactory = new DefaultThreadFactory("rendezvous");
        final EventLoopGroup connectGroup = new NioEventLoopGroup(1, connectFactory, NioUdtProvider.MESSAGE_PROVIDER);
        try {
            final Bootstrap boot = new Bootstrap();
            boot.group(connectGroup)
                    .channelFactory(NioUdtProvider.MESSAGE_RENDEZVOUS)
                    .handler(new ChannelInitializer<UdtChannel>() {
                        @Override
                        protected void initChannel(UdtChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO), new MsgEchoPeerHandler(messageSize));
                        }
                    });

            ChannelFuture future = boot.connect(peer, self).sync();
            future.channel().closeFuture().sync();
        } finally {
            connectGroup.shutdownGracefully();
        }
    }

}
