package udt.echo.rendezvousBytes;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.udt.nio.NioUdtProvider;

public class ByteEchoPeerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final ByteBuf message;

    public ByteEchoPeerHandler(final int messageSize) {
        super(false);
        message = Unpooled.buffer(messageSize);
        for (int i = 0; i < message.capacity(); i++) {
            message.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.err.println("ECHO active " + NioUdtProvider.socketUDT(ctx.channel()).toStringOptions());
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) {
        ctx.write(buf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
