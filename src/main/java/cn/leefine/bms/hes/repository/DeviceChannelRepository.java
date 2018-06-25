package cn.leefine.bms.hes.repository;

import cn.leefine.bms.hes.constant.RepositoryConstant;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * 设备与通道对应关系，设备号做主键，找到设备号就可以找到通道
 */
public class DeviceChannelRepository {
    /***
     * key:device no  ; value: Channel id
     */
    private static ConcurrentHashMap<String, String> deviceChannels = new ConcurrentHashMap<String, String>(RepositoryConstant.DEVICE_CHANNEL_REPOSITORY_COUNT);

    public static void put(String key, String value) {
        deviceChannels.put(key, value);
    }

    public static void remove(String key) {
        deviceChannels.remove(key);
    }

    public static void removeByChannelID(String channelID) {
        for (Iterator<Map.Entry<String, String>> it = deviceChannels.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> item = it.next();
            if (item.getValue().equals(channelID)) {
                it.remove();
                break;
            }
        }
    }

    public static Map<String, String> getDeviceChannels() {
        return deviceChannels;
    }

    public static String get(String key) {
        return deviceChannels.get(key);
    }

    public static boolean exist(String key) {
        return deviceChannels.get(key) != null;
    }

    public static int size() {
        return deviceChannels.size();
    }
}