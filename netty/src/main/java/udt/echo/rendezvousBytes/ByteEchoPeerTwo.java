package udt.echo.rendezvousBytes;

import io.netty.util.internal.SocketUtils;
import udt.echo.rendezvous.Config;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ByteEchoPeerTwo extends ByteEchoPeerBase {

    public ByteEchoPeerTwo(int messageSize, SocketAddress myAddress, SocketAddress peerAddress) {
        super(messageSize, myAddress, peerAddress);
    }

    public static void main(String[] args) throws Exception {
        final int messageSize = 64 * 1024;
        final InetSocketAddress myAddress = SocketUtils.socketAddress(Config.hostTwo, Config.portTwo);
        final InetSocketAddress peerAddress = SocketUtils.socketAddress(Config.hostOne, Config.portOne);
        new ByteEchoPeerTwo(messageSize, myAddress, peerAddress).run();
    }
}