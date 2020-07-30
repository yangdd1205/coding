package org.yangdongdong.delay.task;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SystemTimer implements Timer {

    private DelayQueue<TimerTaskList> delayQueue = new DelayQueue<>();

    private TimingWheel timingWheel;

    /**
     * 负责执行到期的任务
     */
    private ExecutorService executor = Executors.newFixedThreadPool(1);


    public SystemTimer(Long tickMs, Integer wheelSize, Long startMs) {
        timingWheel = new TimingWheel(tickMs, wheelSize, startMs, delayQueue);
    }


    @Override
    public void add(TimerTask timerTask) {
        addTimerTaskEntry(new TimerTaskEntry(timerTask, timerTask.getDelayMs() + System.nanoTime() / 1_000_000));
    }

    private void addTimerTaskEntry(TimerTaskEntry timerTaskEntry) {
        if (!timingWheel.add(timerTaskEntry)) {
            executor.submit(timerTaskEntry.getTimerTask());
        }
    }

    @Override
    public synchronized Boolean advanceClock(Long timeouts) {
        TimerTaskList bucket = null;
        try {
            bucket = delayQueue.poll(timeouts, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (bucket != null) {
            while (bucket != null) {
                timingWheel.advanceClock(bucket.getExpiration());
                bucket.flush((timerTaskEntry) -> {
                    addTimerTaskEntry(timerTaskEntry);
                });
                bucket = delayQueue.poll();
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }
}
