package com.ymd.netty.firstexample;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayMessage implements Delayed {
    private String message;   // 延迟任务中的任务数据
    private long ttl;         // 延迟任务到期时间（过期时间）

    /**
     * 构造函数
     * @param message 消息实体
     * @param ttl 延迟时间，单位毫秒
     */
    public DelayMessage(String message, long ttl) {
        setMessage(message);
        this.ttl = System.currentTimeMillis() + ttl;
    }

    @Override
    public int compareTo(Delayed o) {
        // 比较、排序：对任务的延时大小进行排序，将延时时间最小的任务放到队列头部
        return 1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 计算该任务距离过期还剩多少时间
        long remaining = ttl - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }
}
