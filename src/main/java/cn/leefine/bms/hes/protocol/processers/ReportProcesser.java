package cn.leefine.bms.hes.protocol.processers;

import cn.leefine.bms.hes.protocol.DeviceFrame;
import cn.leefine.bms.hes.protocol.DeviceProcess;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Created by LeefineChan on 6/23/2018.
 */

public class ReportProcesser implements DeviceProcess {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ReportProcesser.class);
    //回复登陆，更新设备列表
    @Override
    public void process(ChannelHandlerContext ctx, DeviceFrame frame) {
        if (LoginProcesser.checkLogin(ctx, frame)) {

            //获取上报数据并处理
        }
    }
}
