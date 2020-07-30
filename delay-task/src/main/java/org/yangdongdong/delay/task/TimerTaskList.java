package org.yangdongdong.delay.task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TimerTaskList implements Delayed {


    private AtomicInteger taskCounter;

    private TimerTaskEntry root;


    public TimerTaskList(AtomicInteger taskCounter) {
        this.taskCounter = taskCounter;
        root = new TimerTaskEntry(null, -1L);
        root.setPrev(root);
        root.setNext(root);
    }


    private AtomicLong expiration = new AtomicLong(-1);


    /**
     * 设置过期时间
     *
     * @param expirationMs
     * @return true 过期时间改变，false 过期时间没改变
     */
    public Boolean setExpiration(Long expirationMs) {
        return expiration.getAndSet(expirationMs) != expirationMs;
    }

    public Long getExpiration() {
        return expiration.get();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(Long.max(expiration.get() - System.nanoTime() / 1_000_000, 0), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        TimerTaskList timerTaskList = (TimerTaskList) o;
        return Long.compare(this.expiration.get(), timerTaskList.expiration.get());
    }


    public synchronized void add(TimerTaskEntry timerTaskEntry) {
        synchronized (timerTaskEntry) {
            timerTaskEntry.remove();//先移除，不然会出现死循环
            boolean done = false;
            while (!done) {
                if (timerTaskEntry.getTimerTaskList() == null) {
                    TimerTaskEntry tail = root.getPrev();

                    tail.setNext(timerTaskEntry);
                    timerTaskEntry.setPrev(tail);
                    timerTaskEntry.setNext(root);
                    root.setPrev(timerTaskEntry);

                    timerTaskEntry.setTimerTaskList(this);
                    done = true;
                }
            }

        }
    }

    public synchronized void flush(Flush flush) {
        TimerTaskEntry head = root.getNext();
        while (head != root) {
            remove(head);
            flush.doFlush(head);
            head = root.getNext();
        }
        this.setExpiration(-1L);
    }

    public synchronized void remove(TimerTaskEntry timerTaskEntry) {
        synchronized (timerTaskEntry) {
            if (timerTaskEntry.getTimerTaskList() == this) {
                timerTaskEntry.getNext().setPrev(timerTaskEntry.getPrev());
                timerTaskEntry.getPrev().setNext(timerTaskEntry.getNext());
                timerTaskEntry.setNext(null);
                timerTaskEntry.setPrev(null);
                timerTaskEntry.setTimerTaskList(null);
            }

        }
    }


    @FunctionalInterface
    interface Flush {
        void doFlush(TimerTaskEntry timerTaskEntry);
    }
}
