package com.example.kenvin.threadpool;

/**
 * Created by Kenvin on 2017/11/20.
 */


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

/**
 * 1: 创建线程池
 * 2：创建任务
 * 3：执行任务
 */
public class ThreadPoolProxy {

    //只需要创建一次
    ThreadPoolExecutor threadPoolExecutor;


    int corePoolSize;
    int maximumPoolSize;
    long keepAliveTime;

    public ThreadPoolProxy( int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    private ThreadPoolExecutor initThreadPool() {

        if (threadPoolExecutor == null){
            synchronized (ThreadPoolExecutor.class){
                if (threadPoolExecutor==null){
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                    threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,handler);
                }
            }
        }
        return threadPoolExecutor;
    }

    public void execute(Runnable task){
        initThreadPool();
        threadPoolExecutor.execute(task);
    }

    public Future<?>submit(Runnable task){
        initThreadPool();
        return  threadPoolExecutor.submit(task);
    }

    public void remove(Runnable task){
        initThreadPool();
        threadPoolExecutor.remove(task);
    }


}
