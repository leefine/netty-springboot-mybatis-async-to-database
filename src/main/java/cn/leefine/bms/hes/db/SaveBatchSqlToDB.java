package cn.leefine.bms.hes.db;

import cn.leefine.bms.hes.constant.ApplicationPropertiesConstant;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by LeefineChan on 6/25/2018.
 */
public class SaveBatchSqlToDB {

    private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

    public static void offer(String sql) {
        queue.offer(sql);
    }

    public static void save() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BatchSqlService batchSqlService = new BatchSqlService();
                while (true) {
                    if (queue.isEmpty()) {
                        try {
                            Thread.sleep(ApplicationPropertiesConstant.saveDBEmptyWaitSeconds * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String sqlBatch = "", sql = "";
                        for (int i = 0; i < ApplicationPropertiesConstant.saveDBEachtimeCount; i++) {
                            sql = queue.poll();
                            if (null != sql) {
                                sqlBatch += sql;
                            } else {
                                break;
                            }
                        }
                        //TODO:sqlBatch 存库
                        batchSqlService.excBatchSql(sqlBatch);
                        try {
                            Thread.sleep(ApplicationPropertiesConstant.saveDBIntervalSeconds * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
