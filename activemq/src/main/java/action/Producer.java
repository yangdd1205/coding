package action;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    //单例模式

    //1.连接工厂
    private ConnectionFactory connectionFactory;

    //2.连接对象
    private Connection connection;

    //3.Session对象
    private Session session;

    //4.生产者
    private MessageProducer messageProducer;

    public Producer() {
        try {
            connectionFactory = new ActiveMQConnectionFactory("admin", "123456", "tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            messageProducer = session.createProducer(null);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send1() {
        try {
            Destination destination = session.createQueue("first");
            for (int i = 0; i < 100; i++) {
                MapMessage msg = session.createMapMessage();
                int id = i;
                msg.setInt("id", id);
                msg.setString("name", "张" + i);
                msg.setString("age", "" + i);
                String receiver = id % 2 == 0 ? "A" : "B";
                msg.setStringProperty("receiver", receiver);
                messageProducer.send(destination, msg, DeliveryMode.NON_PERSISTENT, 2, 1000 * 60 * 10L);
                System.out.println("message send id:" + id);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        p.send1();
    }
}
