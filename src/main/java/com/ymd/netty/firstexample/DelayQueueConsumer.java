package com.ymd.netty.firstexample;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueConsumer implements Runnable {
//    private final static Logger LOGGER = LoggerFactory.getLogger(DelayQueueConsumer.class);
    private final DelayQueue<DelayMessage> delayQueue;

    /**
     * 构造函数
     * @param delayQueue 延迟队列
     */
    public DelayQueueConsumer(DelayQueue<DelayMessage> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 从延迟队列的头部获取已经过期的消息
                // 如果暂时没有过期消息或者队列为空，则take()方法会被阻塞，直到有过期的消息为止
                DelayMessage delayMessage = delayQueue.take();
                System.out.println(new Date());
//                LOGGER.info("Consumer received message: {}", delayMessage.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
