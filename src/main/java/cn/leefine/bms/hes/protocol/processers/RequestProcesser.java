package cn.leefine.bms.hes.protocol.processers;

import cn.leefine.bms.hes.protocol.DeviceFrame;
import cn.leefine.bms.hes.protocol.DeviceProcess;
import cn.leefine.bms.hes.repository.ChannelRepository;
import cn.leefine.bms.hes.repository.DeviceChannelRepository;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Created by LeefineChan on 6/23/2018.
 */

public class RequestProcesser implements DeviceProcess {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(RequestProcesser.class);
    //回复登陆，更新设备列表
    @Override
    public void process(ChannelHandlerContext ctx, DeviceFrame frame) {
        if (LoginProcesser.checkLogin(ctx, frame)) {

            //获取发送接收方的设备通道号
            String channelID = DeviceChannelRepository.get(frame.getReceiver());

            if (null != channelID) {
                //获取发送接收方的设备通道
                Channel channel = ChannelRepository.get(channelID);
                if (null != channel && channel.isActive())
                    channel.writeAndFlush(frame.toFrame());//向接收方发送信息
                else
                    ctx.channel().writeAndFlush(frame.getReceiver() + " offline");
            } else {
                ctx.channel().writeAndFlush(frame.getReceiver() + " offline");
            }
        }
    }
}
