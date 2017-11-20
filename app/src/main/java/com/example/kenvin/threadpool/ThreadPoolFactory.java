package com.example.kenvin.threadpool;

/**
 * Created by Kenvin on 2017/11/20.
 */

public class ThreadPoolFactory {

    static  ThreadPoolProxy normalThreadPool;

    static  ThreadPoolProxy downLoadThreadPool;

    public  static  ThreadPoolProxy getNormalThreadPool(){
        if (normalThreadPool==null){
            synchronized (ThreadPoolProxy.class){
                if (normalThreadPool == null){
                    normalThreadPool = new ThreadPoolProxy(5,5,3000);
                }
            }
        }
        return normalThreadPool;
    }


    public  static  ThreadPoolProxy getDownLoadThreadPool(){
        if (downLoadThreadPool==null){
            synchronized (ThreadPoolProxy.class){
                if (downLoadThreadPool == null){
                    downLoadThreadPool = new ThreadPoolProxy(3,3,3000);
                }
            }
        }
        return downLoadThreadPool;
    }

}
