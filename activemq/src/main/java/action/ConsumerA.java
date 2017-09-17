package action;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.*;

public class ConsumerA {


    private final String SELECTOR_3 = "receiver = 'A'";

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    private Destination destination;

    public ConsumerA() {

        try {
            connectionFactory = new ActiveMQConnectionFactory("admin", "123456", "tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("first");
            //筛选的是Message的属性 不是消息内容
            messageConsumer = session.createConsumer(destination, SELECTOR_3);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void receiver() {
        try {
            messageConsumer.setMessageListener(new Listener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListener {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000);

        ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 20, 120L, TimeUnit.SECONDS, queue);

        @Override
        public void onMessage(Message message) {
            if (message instanceof MapMessage) {
                MapMessage ret = (MapMessage) message;
                executor.execute(new MessageTask(ret));
            }
        }
    }

    public static void main(String[] args) {
        ConsumerA consumer = new ConsumerA();
        consumer.receiver();
    }
}
