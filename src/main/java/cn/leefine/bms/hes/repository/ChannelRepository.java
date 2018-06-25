package cn.leefine.bms.hes.repository;

import cn.leefine.bms.hes.constant.RepositoryConstant;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/***
 * 通道列表，以短ID作为主键
 */
public class ChannelRepository {
    /***
     * key : Channel id ; value : Channel
     */
    private static ConcurrentMap<String, Channel> channels = new ConcurrentHashMap<String, Channel>(RepositoryConstant.CHANNEL_REPOSITORY_COUNT);

    public static void put(String key, Channel value) {
        channels.put(key, value);
    }

    public static void remove(String key) {
        channels.remove(key);
        //设备通道被移除时，在设备资源表中同时移除
        DeviceChannelRepository.removeByChannelID(key);
    }

    public static Map<String, Channel> getChannels() {
        return channels;
    }

    public static Channel get(String key) {
        return channels.get(key);
    }

    public static int size() {
        return channels.size();
    }
}