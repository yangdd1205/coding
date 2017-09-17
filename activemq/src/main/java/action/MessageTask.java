package action;

import javax.jms.JMSException;
import javax.jms.MapMessage;

public class MessageTask implements Runnable {
    private MapMessage message;

    public MessageTask(MapMessage message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("当前线程：" + Thread.currentThread().getName() + " 处理任务：" + message.getString("id"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
