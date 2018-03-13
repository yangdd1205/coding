package udt.echo.rendezvous;

import io.netty.util.internal.SocketUtils;

import java.net.InetSocketAddress;

public class MsgEchoPeerTwo extends MsgEchoPeerBase {
    protected MsgEchoPeerTwo(InetSocketAddress self, InetSocketAddress peer, int messageSize) {
        super(self, peer, messageSize);
    }

    public static void main(final String[] args) throws Exception {
        final int messageSize = 64 * 1024;
        final InetSocketAddress self = SocketUtils.socketAddress(Config.hostTwo, Config.portTwo);
        final InetSocketAddress peer = SocketUtils.socketAddress(Config.hostOne, Config.portOne);
        new MsgEchoPeerTwo(self, peer, messageSize).run();
    }
}
