package com.dongzj;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池
 * <p>
 * 用ArrayBlockingQueue替换了LinkedBlockingQueue，指定了队列的大小，
 * 当任务超出队列带澳之后使用CallerRunsPolicy拒绝策略处理。
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/26
 * Time: 20:20
 */
public class DongzjThreadPoolExecutor {

    private static ExecutorService executorService = newFixedThreadPool(50);

    private static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10000), new DefaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void execute(Runnable command) {
        executorService.execute(command);
    }

    public static void shutdown() {
        executorService.shutdown();
    }

    static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "DZJ-pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return null;
        }
    }
}
