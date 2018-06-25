package cn.leefine.bms.hes.protocol;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Created by LeefineChan on 6/23/2018.
 */
public class DeviceFrame {
    private String sender;
    private String receiver;
    private String sn;
    private DeviceFrameTypeEnum frameType;
    private String data;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(DeviceFrame.class);

    public DeviceFrame(String msg) throws Exception {
        String[] params = msg.split("[|]");
        this.sender = params[0];
        this.receiver = params[1];
        this.sn = params[2];
        this.frameType = DeviceFrameTypeEnum.values()[Integer.parseInt(params[3])];
        this.data = params[4];
    }

    public String toFrame() {
        return String.format("%s|%s|%s|%s|%s", sender, receiver, sn, frameType.ordinal(), data);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public DeviceFrameTypeEnum getFrameType() {
        return frameType;
    }

    public void setFrameType(DeviceFrameTypeEnum frameType) {
        this.frameType = frameType;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
