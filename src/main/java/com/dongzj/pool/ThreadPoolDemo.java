package com.dongzj.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * submit(Callable task)
 * submit(Runnable task, T result)
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/26
 * Time: 18:04
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolDemo demo = new ThreadPoolDemo();
        demo.submit2();
    }

    public void submit1() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Future<String> future = pool.submit(() -> "hello");
        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public void submit2() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Data data = new Data();
        Future<Data> future = pool.submit(new MyRunnable(data), data);
        try {
            String result = future.get().getName();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class Data {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class MyRunnable implements Runnable {

        private Data data;

        public MyRunnable(Data data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.setName("dongzj");
        }
    }
}
