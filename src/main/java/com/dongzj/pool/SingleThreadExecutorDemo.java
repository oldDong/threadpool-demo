package com.dongzj.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个单线程的线程池
 * <p>
 * 这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
 * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
 * 此线程保证所有任务的执行顺序按照任务的提交顺序执行。
 * 创建原理：
 * return new FinalizableDelegatedExecutorService
 *             (new ThreadPoolExecutor(1, 1,
 *                                     0L, TimeUnit.MILLISECONDS,
 *                                     new LinkedBlockingQueue<Runnable>()));
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/26
 * Time: 15:38
 */
public class SingleThreadExecutorDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 开始发车啦....");
            });
        }
    }
}
