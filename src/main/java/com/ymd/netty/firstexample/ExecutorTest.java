package com.ymd.netty.firstexample;

import javax.activation.CommandInfo;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author didi
 */
public class ExecutorTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                System.out.println("何雨涵");
            });
        }
        executor.shutdown();
    }
}
