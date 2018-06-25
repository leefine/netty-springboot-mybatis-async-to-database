package cn.leefine.bms.hes.protocol;


import cn.leefine.bms.hes.protocol.processers.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 报文处理中转类
 */
public class Processer implements DeviceProcess {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(Processer.class);

    public void process(ChannelHandlerContext ctx, String msg) {
        //需要区分WEb服务和设备发来的报文???
        try {
            //设备报文
            DeviceFrame frame = new DeviceFrame(msg);
            process(ctx, frame);
        } catch (Exception e) {
            logger.error("DeviceFrame Detect Faild," + e.getMessage());
            logger.error("ERR Frame," + msg);
            ctx.channel().writeAndFlush("invalid frame");
        }
    }

    public void process(ChannelHandlerContext ctx, DeviceFrame frame) {
        switch (frame.getFrameType()) {
            case CLIENT_LOGIN: {
                LoginProcesser loginProcesser = new LoginProcesser();
                loginProcesser.process(ctx, frame);
                break;
            }
            case CLIENT_HEATBEAT: {
                HeartBeatProcesser heartBeatProcesser = new HeartBeatProcesser();
                heartBeatProcesser.process(ctx, frame);
                break;
            }
            case CLIENT_REPORT: {
                ReportProcesser reportProcesser = new ReportProcesser();
                reportProcesser.process(ctx, frame);
                break;
            }
            case CLIENT_REQUEST: {
                RequestProcesser requestProcesser = new RequestProcesser();
                requestProcesser.process(ctx, frame);
                break;
            }
            case CLIENT_RESPONSE: {
                ResponseProcesser responseProcesser = new ResponseProcesser();
                responseProcesser.process(ctx, frame);
                break;
            }
            case BROADCAST: {
                BroadcastProcesser broadcastProcesser = new BroadcastProcesser();
                broadcastProcesser.process(ctx, frame);
                break;
            }
            default: {
                ctx.channel().writeAndFlush("undefined frame");
                logger.error("Undefined frame," + frame.toFrame());
                break;
            }
        }
    }

}
