package com.seanzx.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : zhaoxin
 * @date : 2019/7/10.
 */
@Component
public class SchedualTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0/10 * * * * ?")
    public void runTask() {
        logger.info("定时任务执行");
    }
}
