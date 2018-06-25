package cn.leefine.bms.hes.protocol.processers;

import cn.leefine.bms.hes.db.SaveBatchSqlToDB;
import cn.leefine.bms.hes.protocol.DeviceFrame;
import cn.leefine.bms.hes.protocol.DeviceProcess;
import cn.leefine.bms.hes.repository.DeviceChannelRepository;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.time.LocalDateTime;


public class LoginProcesser implements DeviceProcess {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(LoginProcesser.class);


    //回复登陆，更新设备列表
    @Override
    public void process(ChannelHandlerContext ctx, DeviceFrame frame) {
        String channelKey = ctx.channel().id().asShortText();
        DeviceChannelRepository.put(frame.getSender(), channelKey);
        ctx.channel().writeAndFlush("login success");
        logger.debug("login success ： " + frame.toFrame());

        SaveBatchSqlToDB.offer(String.format("insert into log_frame(log) values('%s');", LocalDateTime.now().toString() + frame.toFrame()));
    }

    public static boolean checkLogin(ChannelHandlerContext ctx, DeviceFrame frame) {
        if (DeviceChannelRepository.exist(frame.getSender())) {
            return true;
        } else {
            ctx.channel().writeAndFlush("Login first");
            return false;
        }
    }
}
