package org.ml.mldj.common.utils;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public ThreadPoolExecutor getThreadPool() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 10l, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolExecutor;
    }

    public static void main(String[] args) {
//        int core = Runtime.getRuntime().availableProcessors();
        int core=2;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(core, core * 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));
        threadPoolExecutor.execute(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        for (int i = 0; i < 10; i++) {
            int finalI = i+1;
            System.out.println("开始放入第"+finalI+"大小："+threadPoolExecutor.getPoolSize());
            threadPoolExecutor.execute(()->{
                try {
                    System.out.println("任务"+ finalI);
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println("结束放入第"+finalI+"大小："+threadPoolExecutor.getPoolSize());
        }

    }

}
