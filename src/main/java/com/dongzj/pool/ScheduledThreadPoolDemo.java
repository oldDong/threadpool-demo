package com.dongzj.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 周期性执行任务
 * <p>
 *     创建原理：
 *     super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
 *               new DelayedWorkQueue());
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/26
 * Time: 17:19
 */
public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i++) {
            //延迟10秒执行
            ((ScheduledExecutorService) pool).schedule(() -> {
                System.out.println(Thread.currentThread().getName() + "\t发车啦....");
            }, 10, TimeUnit.SECONDS);
        }

        for (int i = 0; i < 10; i++) {
            ((ScheduledExecutorService) pool).scheduleAtFixedRate(() -> {
                System.out.println(Thread.currentThread().getName() + "\t发车啦....");
            }, 1, 1, TimeUnit.SECONDS);
        }
    }
}
