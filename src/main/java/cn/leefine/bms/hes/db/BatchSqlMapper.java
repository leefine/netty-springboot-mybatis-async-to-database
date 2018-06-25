package cn.leefine.bms.hes.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by LeefineChan on 6/25/2018.
 */
@Mapper
public interface BatchSqlMapper {
    @Insert("${sql}")
    void excBatchSql(@Param("sql") String sql);
}