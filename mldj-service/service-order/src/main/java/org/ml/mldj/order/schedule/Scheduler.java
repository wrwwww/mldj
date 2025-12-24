package org.ml.mldj.order.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(fixedRate = 5000, cron = "0 */5 * * * *")
    public void schedule() {

    }
}
