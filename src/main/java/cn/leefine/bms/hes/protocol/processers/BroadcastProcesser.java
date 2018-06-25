package cn.leefine.bms.hes.protocol.processers;

import cn.leefine.bms.hes.protocol.DeviceFrame;
import cn.leefine.bms.hes.protocol.DeviceProcess;
import cn.leefine.bms.hes.repository.ChannelRepository;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.Map;

/**
 * Created by LeefineChan on 6/23/2018.
 */

public class BroadcastProcesser implements DeviceProcess {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(BroadcastProcesser.class);
    //消息广播，发给其他所有在线通道
    @Override
    public void process(ChannelHandlerContext ctx, DeviceFrame frame) {
        if (LoginProcesser.checkLogin(ctx, frame)) {
            for (Map.Entry<String, Channel> entry : ChannelRepository.getChannels().entrySet()) {
                if (ctx.channel().id().asShortText().equals(entry.getKey())) continue;

                Channel channel = entry.getValue();
                channel.writeAndFlush(frame.toFrame());//向接收方发送信息
            }
        }
    }
}
