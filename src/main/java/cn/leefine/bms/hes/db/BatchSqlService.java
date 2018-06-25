package cn.leefine.bms.hes.db;

import cn.leefine.bms.hes.utils.SpringContextUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Created by LeefineChan on 6/25/2018.
 */

public class BatchSqlService implements BatchSqlMapper {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(BatchSqlService.class);
    private BatchSqlMapper batchSqlMapper;

    public BatchSqlService() {
        batchSqlMapper = (BatchSqlMapper) SpringContextUtil.getBean("batchSqlMapper");
    }

    @Override
    public void excBatchSql(String sql) {
        batchSqlMapper.excBatchSql(sql);
    }
}
