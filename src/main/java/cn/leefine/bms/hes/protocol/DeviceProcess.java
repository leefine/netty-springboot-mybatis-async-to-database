package cn.leefine.bms.hes.protocol;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by LeefineChan on 6/23/2018.
 */
public interface DeviceProcess {
    void process(ChannelHandlerContext ctx, DeviceFrame frame);
}
