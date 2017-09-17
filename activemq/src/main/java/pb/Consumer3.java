package pb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer3 {

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    private Destination destination;

    public Consumer3() {

        try {
            connectionFactory = new ActiveMQConnectionFactory("admin", "123456", "tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("topic1");
            //筛选的是Message的属性 不是消息内容
            messageConsumer = session.createConsumer(destination);
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
                TextMessage ret = (TextMessage) message;
                System.out.println("C3 收到消息-----");
                try {
                    System.out.println(ret.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            } else if (message instanceof MapMessage) {
            }
        }
    }

    public static void main(String[] args) {
        Consumer3 consumer = new Consumer3();
        consumer.receiver();
    }
}
