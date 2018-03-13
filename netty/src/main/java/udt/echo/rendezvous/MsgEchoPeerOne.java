package udt.echo.rendezvous;

import io.netty.util.internal.SocketUtils;

import java.net.InetSocketAddress;

public class MsgEchoPeerOne extends MsgEchoPeerBase {
    protected MsgEchoPeerOne(InetSocketAddress self, InetSocketAddress peer, int messageSize) {
        super(self, peer, messageSize);
    }

    public static void main(String[] args) throws Exception{
        final int messageSize = 64 * 1024;
        final InetSocketAddress self = SocketUtils.socketAddress(Config.hostOne, Config.portOne);
        final InetSocketAddress peer = SocketUtils.socketAddress(Config.hostTwo, Config.portTwo);
        new MsgEchoPeerOne(self, peer, messageSize).run();
    }
}
