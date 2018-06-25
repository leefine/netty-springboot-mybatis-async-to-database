package cn.leefine.bms.hes.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by LeefineChan on 6/25/2018.
 */
@Component
public class ApplicationPropertiesConstant {
    public static int tcpPort;
    public static int bossCount;
    public static int workerCount;
    public static int saveDBIntervalSeconds;
    public static int saveDBEmptyWaitSeconds;
    public static int saveDBEachtimeCount;

    @Value("${tcp.port}")
    public void setTcpPort(int tcpPort) {
        ApplicationPropertiesConstant.tcpPort = tcpPort;
    }

    @Value("${boss.thread.count}")
    public void setBossCount(int bossCount) {
        ApplicationPropertiesConstant.bossCount = bossCount;
    }

    @Value("${worker.thread.count}")
    public void setWorkerCount(int workerCount) {
        ApplicationPropertiesConstant.workerCount = workerCount;
    }

    @Value("${savedb.interval.seconds}")
    public void setSaveDBIntervalSeconds(int saveDBIntervalSeconds) {
        ApplicationPropertiesConstant.saveDBIntervalSeconds = saveDBIntervalSeconds;
    }

    @Value("${savedb.empty.wait.seconds}")
    public void setSaveDBEmptyWaitSeconds(int saveDBEmptyWaitSeconds) {
        ApplicationPropertiesConstant.saveDBEmptyWaitSeconds = saveDBEmptyWaitSeconds;
    }

    @Value("${savedb.eachtime.count}")
    public void setSaveDBEachtimeCount(int saveDBEachtimeCount) {
        ApplicationPropertiesConstant.saveDBEachtimeCount = saveDBEachtimeCount;
    }
}
