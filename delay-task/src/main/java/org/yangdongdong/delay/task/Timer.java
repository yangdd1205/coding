package org.yangdongdong.delay.task;


public interface Timer {

    void add(TimerTask timerTask);


    Boolean advanceClock(Long timeouts);


    void shutdown();


}
