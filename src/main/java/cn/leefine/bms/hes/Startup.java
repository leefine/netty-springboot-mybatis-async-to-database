package cn.leefine.bms.hes;

import cn.leefine.bms.hes.db.SaveBatchSqlToDB;
import cn.leefine.bms.hes.netty.NettyServer;
import cn.leefine.bms.hes.utils.SpringContextUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Startup {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(Startup.class);

    public static void main(String[] args) {
        logger.debug("Begin to start ....");
        ConfigurableApplicationContext context = SpringApplication.run(Startup.class, args);
        SpringContextUtil.setApplicationContext(context);

        SaveBatchSqlToDB.save();//批量存库线程
        context.getBean(NettyServer.class).start();//主线程
    }
}
