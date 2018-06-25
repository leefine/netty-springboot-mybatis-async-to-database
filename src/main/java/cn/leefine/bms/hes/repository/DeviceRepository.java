package cn.leefine.bms.hes.repository;

import cn.leefine.bms.hes.constant.RepositoryConstant;
import cn.leefine.bms.hes.beans.Device;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * 系统当前接入设备，从数据库加载，需要定时更新
 */
public class DeviceRepository {
    /***
     * key:device no  ; value: device object
     */
    private static ConcurrentHashMap<String, Device> devices = new ConcurrentHashMap<String, Device>(RepositoryConstant.DEVICE_REPOSITORY_COUNT);

    public static void put(String key, Device value) {
        devices.put(key, value);
    }

    public static void remove(String key) {
        devices.remove(key);
    }

    public static Map<String, Device> getDevice() {
        return devices;
    }

    public static Device get(String key) {
        return devices.get(key);
    }

    public static int size() {
        return devices.size();
    }
}