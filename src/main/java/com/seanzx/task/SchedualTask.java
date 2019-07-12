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

    /**
     * spring cron执行规则:
     * | 代表意义 | 秒 |分钟 | 小时 | 日期 | 月份 | 周 |
     * | 数字范围 |0-59|0-59| 0-23 | 1-31| 1-12 |0-7|
     *
     * 周的数字为 0 或 7 时，都代表"星期天"的意思
     *
     *   *（星号） 代表任何时刻都会执行
     *   ,(逗号)  分隔时段，满足前后都会执行
     *   /(斜线)  代表每隔...会执行一次
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void runTask() {
        logger.info("每10秒执行一次");
    }
}
