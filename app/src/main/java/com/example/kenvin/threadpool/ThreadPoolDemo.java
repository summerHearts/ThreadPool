package com.example.kenvin.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kenvin on 2017/11/20.
 */

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // ExecutorService es = Executors.newSingleThreadExecutor();
        // ExecutorService es = Executors.newFixedThreadPool(2);
        // ExecutorService es = Executors.newCachedThreadPool();
        ExecutorService es = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 111; i++) {
            MyThread task = new MyThread();
            es.execute(task);
        }
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThread-task");
    }

}
