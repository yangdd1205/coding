package org.yangdongdong.delay.task;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TimingWheel {

    /**
     * 格子的时间跨度
     */
    private Long tickMs;

    /**
     * 轮子中格子的多少
     */
    private Integer wheelSize;

    /**
     * 每个格子对应的
     */
    private TimerTaskList[] buckets;


    private Long currentTime;

    private TimingWheel overflowWheel;

    private Long interval;


    private DelayQueue<TimerTaskList> queue;

    /**
     * 任务计数器
     */
    private AtomicInteger taskCounter;


    public TimingWheel(Long tickMs, Integer wheelSize, Long startMs, DelayQueue<TimerTaskList> queue) {
        this.tickMs = tickMs;
        this.wheelSize = wheelSize;
        this.interval = tickMs * wheelSize;// 当前时间轮的总时间跨度
        this.currentTime = startMs - (startMs % tickMs);//将 currentTime 修剪为 tickMs 的整数倍
        this.buckets = new TimerTaskList[wheelSize];
        this.queue = queue;

        for (Integer i = 0; i < wheelSize; i++) {
            TimerTaskList timerTaskList = new TimerTaskList(taskCounter);
            buckets[i] = timerTaskList;
        }
    }


    private void addOverflowWheel() {
        synchronized (this) {
            if (overflowWheel == null) {
                overflowWheel = new TimingWheel(interval, wheelSize, currentTime, queue);
            }
        }
    }


    public Boolean add(TimerTaskEntry timerTaskEntry) {
        Long expirationMs = timerTaskEntry.getExpirationMs();

        if (expirationMs < this.currentTime + tickMs) {
            // 已经到期
            return false;
        } else if (expirationMs < this.currentTime + interval) {
            // 放到自己的 bucket 中
            long index = expirationMs / tickMs;
            TimerTaskList bucket = buckets[(int) (index % wheelSize)];
            bucket.add(timerTaskEntry);
            // 如果过期时间改了， 重新放入 delay
            if (bucket.setExpiration(index * tickMs)) {
                queue.offer(bucket);
            }
            return true;
        } else {
            if (overflowWheel == null) {
                addOverflowWheel();
            }
            return overflowWheel.add(timerTaskEntry);
        }
    }


    public void advanceClock(Long timeMs) {
        if (timeMs >= currentTime + tickMs) {
            currentTime = timeMs - (timeMs % tickMs);
            if (overflowWheel != null) {
                overflowWheel.advanceClock(currentTime);
            }
        }
    }
}
