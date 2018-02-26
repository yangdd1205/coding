package http.snoop;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;

public class HttpSnoopClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;

            System.err.println("STATUS: " + response.getStatus());
            System.err.println("VERSION: " + response.getProtocolVersion());
            System.err.println();

            if (!response.headers().isEmpty()) {
                response.headers().names().forEach(name -> response.headers().getAll(name).forEach(value -> System.err.println("HEADER: " + name + " = " + value)));
                System.err.println();
            }
            if (HttpHeaders.isTransferEncodingChunked(response)) {
                System.err.println("CHUNKED CONTENT {");
            }else {
                System.err.println("CONTENT {");
            }
        }

        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            System.err.println(content.content().toString(CharsetUtil.UTF_8));
            System.err.flush();

            if (msg instanceof LastHttpContent) {
                System.err.println("} END OF CONTENT");
                ctx.close();
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
