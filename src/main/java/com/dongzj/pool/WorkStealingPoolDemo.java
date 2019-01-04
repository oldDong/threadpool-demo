package com.dongzj.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1.8新增线程池
 * 会根据所需的并行层次来动态创建和关闭线程，通过使用多个队列减少仅增，底层用的ForkJoin来实现。
 * ForkJoinPool的优势在于，可以充分利用多cpu，多核的优势，把一个任务拆分成多个"小人物"，把多个"小任务"
 * 放到多个处理器核心上并行执行；当多个"小任务"执行完成之后，再将这些执行结果合并起来即可。
 * <p>
 *     创建原理：
 *  *     return new ForkJoinPool
 *  *             (Runtime.getRuntime().availableProcessors(),
 *  *              ForkJoinPool.defaultForkJoinWorkerThreadFactory,
 *  *              null, true);
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/26
 * Time: 17:24
 */
public class WorkStealingPoolDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newWorkStealingPool();
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + "\t发车啦...."));
        }
    }
}
