package sctp.multihoming;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.SctpChannel;
import io.netty.channel.sctp.SctpChannelOption;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.util.internal.SocketUtils;
import sctp.SctpEchoClientHandler;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public final class SctpMultiHomingEchoClient {

    private static final String CLIENT_PRIMARY_HOST = System.getProperty("host.primary", "127.0.0.1");
    private static final String CLIENT_SECONDARY_HOST = System.getProperty("host.secondary", "127.0.0.2");

    private static final int CLIENT_PORT = Integer.parseInt(System.getProperty("port.local", "8008"));

    private static final String SERVER_REMOTE_HOST = System.getProperty("host.remote", "127.0.0.1");
    private static final int SERVER_REMOTE_PORT = Integer.parseInt(System.getProperty("port.remote", "8007"));

    public static void main(String[] args) throws Exception {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSctpChannel.class)
             .option(SctpChannelOption.SCTP_NODELAY, true)
             .handler(new ChannelInitializer<SctpChannel>() {
                 @Override
                 public void initChannel(SctpChannel ch) throws Exception {
                     ch.pipeline().addLast(
//                             new LoggingHandler(LogLevel.INFO),
                             new SctpEchoClientHandler());
                 }
             });

            InetSocketAddress localAddress = SocketUtils.socketAddress(CLIENT_PRIMARY_HOST, CLIENT_PORT);
            InetAddress localSecondaryAddress = SocketUtils.addressByName(CLIENT_SECONDARY_HOST);

            InetSocketAddress remoteAddress = SocketUtils.socketAddress(SERVER_REMOTE_HOST, SERVER_REMOTE_PORT);

            // Bind the client channel.
            ChannelFuture bindFuture = b.bind(localAddress).sync();

            // Get the underlying sctp channel
            SctpChannel channel = (SctpChannel) bindFuture.channel();

            // Bind the secondary address.
            // Please note that, bindAddress in the client channel should be done before connecting if you have not
            // enable Dynamic Address Configuration. See net.sctp.addip_enable kernel param
            channel.bindAddress(localSecondaryAddress).sync();

            // Finish connect
            ChannelFuture connectFuture = channel.connect(remoteAddress).sync();

            // Wait until the connection is closed.
            connectFuture.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}