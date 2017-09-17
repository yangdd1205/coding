package pb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publish {

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

    public Publish() {
        try {
            connectionFactory = new ActiveMQConnectionFactory("admin", "123456", "tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(null);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void sendMessage() throws JMSException {
        Destination destination = session.createTopic("topic1");
        TextMessage textMessage = session.createTextMessage("我是内容");
        messageProducer.send(destination, textMessage);
        connection.close();

    }

    public static void main(String[] args) throws JMSException {
        Publish p = new Publish();
        p.sendMessage();
    }
}
