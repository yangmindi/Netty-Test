package com.ymd.netty.firstexample;

import io.netty.util.CharsetUtil;
import org.omg.PortableInterceptor.INACTIVE;
import sun.rmi.runtime.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class 获取当天时间0点 {
    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//        Date beginOfDate = cal.getTime();
//        System.out.println(beginOfDate);

//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName()+"开始执行");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    System.out.println(Thread.currentThread().getName()+"执行完成");
//                }
//            }
//        });
//
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName()+"开始执行");
//                System.out.println(Thread.currentThread().getName()+"等待线程1");
//                try {
//                    thread1.join();
//                    System.out.println(Thread.currentThread().getName()+"继续执行");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    System.out.println(Thread.currentThread().getName()+"执行完成");
//                }
//            }
//        });
//
//        thread1.start();
//        thread2.start();

//        Object share1 = new Object();
//        Object share2 = new Object();
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (share1){
//                    try {
//                        System.out.println(Thread.currentThread().getName()+"正在运行");
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (share2){
//                        System.out.println(Thread.currentThread().getName()+"申请share2");
//                    }
//                }
//            }
//        });
//
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (share2){
//                    try {
//                        System.out.println(Thread.currentThread().getName()+"正在运行");
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (share1){
//                        System.out.println(Thread.currentThread().getName()+"申请share1");
//                    }
//                }
//            }
//        });
//
//        thread.start();
//        thread1.start();
        int[] a = new int[]{1,-1};
//        System.out.println(maxSlidingWindow(a,1));
    }
}
