package cn.leefine.bms.hes.db;

import cn.leefine.bms.hes.protocol.processers.LoginProcesser;
import cn.leefine.bms.hes.utils.SpringContextUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by LeefineChan on 6/25/2018.
 */

public class DeviceService implements DeviceMapper {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(LoginProcesser.class);

    private DeviceMapper deviceMapper;

    public DeviceService() {
        deviceMapper = (DeviceMapper) SpringContextUtil.getBean("deviceMapper");
    }


    @Override
    public List<Map<String, Object>> getDevice() {
        return deviceMapper.getDevice();
    }


}
