package p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {


    private final String SELECTOR_0 = "age > 25";
    private final String SELECTOR_1 = "color = 'blue'";
    private final String SELECTOR_2 = "color = 'blue' AND sal > 2000 ";
    private final String SELECTOR_3 = "receiver = 'A'";

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    private Destination destination;

    public Consumer() {

        try {
            connectionFactory = new ActiveMQConnectionFactory("admin", "123456", "tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("first");
            //筛选的是Message的属性 不是消息内容
            messageConsumer = session.createConsumer(destination,SELECTOR_2);
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

        @Override
        public void onMessage(Message message) {
            if (message instanceof TextMessage) {

            } else if (message instanceof MapMessage) {
                MapMessage ret = (MapMessage) message;
                System.out.println(ret.toString());
                try {
                    System.out.println(ret.getString("name"));
                    System.out.println(ret.getString("age"));
                    System.out.println();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.receiver();
    }
}
