package p2p;

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
            MapMessage msg1 = session.createMapMessage();
            //设置消息的内容
            msg1.setString("name", "张三");
            msg1.setString("age", "23");
            //设置消息的属性
            msg1.setStringProperty("color", "blue");
            msg1.setIntProperty("sal", 2200);

            MapMessage msg2 = session.createMapMessage();
            msg2.setString("name", "李四");
            msg2.setString("age", "26");
            msg2.setStringProperty("color", "red");
            msg2.setIntProperty("sal", 1300);


            MapMessage msg3 = session.createMapMessage();
            msg3.setString("name", "王五");
            msg3.setString("age", "28");
            msg3.setStringProperty("color", "green");
            msg3.setIntProperty("sal", 1500);


            MapMessage msg4 = session.createMapMessage();
            msg4.setString("name", "赵六");
            msg4.setString("age", "30");
            msg4.setStringProperty("color", "blue");
            msg4.setIntProperty("sal", 1800);


            messageProducer.send(destination, msg1, DeliveryMode.NON_PERSISTENT, 2, 1000 * 60 * 10L);
            messageProducer.send(destination, msg2, DeliveryMode.NON_PERSISTENT, 3, 1000 * 60 * 10L);
            messageProducer.send(destination, msg3, DeliveryMode.NON_PERSISTENT, 6, 1000 * 60 * 10L);
            messageProducer.send(destination, msg4, DeliveryMode.NON_PERSISTENT, 9, 1000 * 60 * 10L);
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        p.send1();
    }
}
