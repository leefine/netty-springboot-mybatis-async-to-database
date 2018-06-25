package cn.leefine.bms.hes.netty;

import cn.leefine.bms.hes.repository.ChannelRepository;
import cn.leefine.bms.hes.protocol.Processer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class ServiceHandler extends ChannelInboundHandlerAdapter {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ServiceHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        String channelKey = ctx.channel().id().asShortText();
        ChannelRepository.put(channelKey, ctx.channel());
        logger.info(channelKey + ":Active ; IP:" + ctx.channel().remoteAddress().toString());
        // ctx.writeAndFlush("Your channel key is " + channelKey + "\r\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //String message = msg.toString();
        logger.debug("Receive:" + msg.toString());
        Processer processer = new Processer();
        processer.process(ctx, msg.toString());
      /*  ctx.channel().writeAndFlush("A|TEST|11").addListener(future -> {
            if (future.isSuccess()) {
                logger.info("success");
            } else {
                logger.info("fail");
            }
        });*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(cause.getMessage(), cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String channelKey = ctx.channel().id().asShortText();
        ChannelRepository.remove(channelKey);
        logger.debug(channelKey + ":Inactive ; IP:" + ctx.channel().remoteAddress().toString());
    }
}
