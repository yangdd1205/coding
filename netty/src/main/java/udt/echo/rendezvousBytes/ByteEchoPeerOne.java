package udt.echo.rendezvousBytes;

import io.netty.util.internal.SocketUtils;
import udt.echo.rendezvous.Config;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ByteEchoPeerOne extends ByteEchoPeerBase {

    public ByteEchoPeerOne(int messageSize, SocketAddress myAddress, SocketAddress peerAddress) {
        super(messageSize, myAddress, peerAddress);
    }

    public static void main(String[] args) throws Exception {
        final int messageSize = 64 * 1024;
        final InetSocketAddress myAddress = SocketUtils.socketAddress(Config.hostOne, Config.portOne);
        final InetSocketAddress peerAddress = SocketUtils.socketAddress(Config.hostTwo, Config.portTwo);
        new ByteEchoPeerOne(messageSize, myAddress, peerAddress).run();
    }
}