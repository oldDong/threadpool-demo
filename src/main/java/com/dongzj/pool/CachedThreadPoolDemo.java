package com.dongzj.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可缓存的线程池
 * <p>
 * 如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲的线程，
 * 当任务数增加时，此线程池又添加新线程来处理任务。
 * <p>
 *     创建原理:
 *     return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
 *                                       60L, TimeUnit.SECONDS,
 *                                       new SynchronousQueue<Runnable>());
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/26
 * Time: 16:01
 */
public class CachedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + "发车啦...."));
        }
    }
}
