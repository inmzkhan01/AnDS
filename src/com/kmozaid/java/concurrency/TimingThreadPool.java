package com.kmozaid.java.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimingThreadPool extends ThreadPoolExecutor {

    private ThreadLocal<Long> startTime;

    public TimingThreadPool(int corePoolSize,
                            int maximumPoolSize,
                            long keepAliveTime,
                            TimeUnit unit,
                            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        try {
            startTime.set(System.currentTimeMillis());
        } finally {
            super.beforeExecute(t, r);
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        System.out.println(Thread.currentThread().getName() + " took " + (System.currentTimeMillis() - startTime.get()) + "ms");
    }

}
