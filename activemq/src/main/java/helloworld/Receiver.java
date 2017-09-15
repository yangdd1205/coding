package helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {

    public static void main(String[] args) throws Exception {
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "123456", "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //通过Connection对象创建Session会话（上下文环境对象），用于接收对象，参数配置1为是否启用事物，参数配置2为签收模式，一般我们设置自动签收
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("first");

        MessageConsumer consumer = session.createConsumer(queue);

        while (true) {
            TextMessage msg = (TextMessage) consumer.receive();
            System.out.println("消费数据：" + msg.getText());
        }
    }
}
