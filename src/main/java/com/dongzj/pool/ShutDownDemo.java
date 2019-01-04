package com.dongzj.pool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/26
 * Time: 19:44
 */
public class ShutDownDemo {

    public static void main(String[] args) throws Exception {
        ShutDownDemo demo = new ShutDownDemo();
//        demo.shutdownnow();
        demo.shutdown();
    }

    public void shutdownnow() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            System.err.println(i);
            pool.execute(() -> {
                try {
                    Thread.sleep(80000);
                    System.out.println("--");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(1000);
        List<Runnable> runs = pool.shutdownNow();
        for (Runnable runnable : runs) {
            System.out.println(runnable);
        }
    }

    public void shutdown() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            System.err.println(i);
            pool.execute(() -> {
                try {
                    Thread.sleep(30000);
                    System.out.println("--");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(1000);
        pool.shutdown();
        pool.execute(() -> {
            try {
                Thread.sleep(30000);
                System.out.println("--");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
