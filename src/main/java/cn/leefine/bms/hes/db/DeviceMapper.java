package cn.leefine.bms.hes.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by LeefineChan on 6/25/2018.
 */
@Mapper
public interface DeviceMapper {
    @Select("SELECT *  FROM  ACHV_DEVICE")
    List<Map<String, Object>> getDevice();
}