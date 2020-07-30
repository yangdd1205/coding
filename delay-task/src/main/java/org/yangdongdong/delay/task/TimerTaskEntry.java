package org.yangdongdong.delay.task;

class TimerTaskEntry implements Comparable<TimerTaskEntry> {

    /**
     * 声明为 volatile 变量是为了可见性
     */
    private volatile TimerTaskEntry next;
    private volatile TimerTaskEntry prev;


    private volatile TimerTaskList timerTaskList;


    private Runnable timerTask;

    private Long expirationMs;


    public TimerTaskEntry(TimerTask timerTask, Long expirationMs) {
        this.timerTask = timerTask;
        this.expirationMs = expirationMs;
    }

    public void remove() {
        TimerTaskList currentList = timerTaskList;
        while (currentList != null) {
            currentList.remove(this);
            currentList = timerTaskList;
        }
    }

    @Override
    public int compareTo(TimerTaskEntry o) {
        return Long.compare(expirationMs, o.expirationMs);
    }


    public Long getExpirationMs() {
        return expirationMs;
    }

    public TimerTaskEntry getNext() {
        return next;
    }

    public void setNext(TimerTaskEntry next) {
        this.next = next;
    }

    public TimerTaskEntry getPrev() {
        return prev;
    }

    public void setPrev(TimerTaskEntry prev) {
        this.prev = prev;
    }

    public Runnable getTimerTask() {
        return timerTask;
    }

    public TimerTaskList getTimerTaskList() {
        return timerTaskList;
    }

    public void setTimerTaskList(TimerTaskList timerTaskList) {
        this.timerTaskList = timerTaskList;
    }
}
