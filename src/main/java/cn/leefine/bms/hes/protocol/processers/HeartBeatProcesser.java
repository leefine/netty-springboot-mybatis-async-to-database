package cn.leefine.bms.hes.protocol.processers;

import cn.leefine.bms.hes.protocol.DeviceFrame;
import cn.leefine.bms.hes.protocol.DeviceProcess;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Created by LeefineChan on 6/23/2018.
 */

public class HeartBeatProcesser implements DeviceProcess {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(HeartBeatProcesser.class);
    @Override
    public void process(ChannelHandlerContext ctx, DeviceFrame frame) {
        if (LoginProcesser.checkLogin(ctx, frame))
            ctx.channel().writeAndFlush("HeartBeat success");
    }
}
