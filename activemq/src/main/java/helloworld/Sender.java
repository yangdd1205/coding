package helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class Sender {

    public static void main(String[] args) throws Exception {
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "123456", "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //通过Connection对象创建Session会话（上下文环境对象），用于接收对象，参数配置1为是否启用事物，参数配置2为签收模式，一般我们设置自动签收
        //当支持事物时，签收模式模式是SESSION_TRANSACTED
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("first");

        MessageProducer producer = session.createProducer(null);
        //消息是否持久化
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for (int i = 0; i < 10; i++) {
            TextMessage msg = session.createTextMessage("我是消息内容" + i);

            // 第一个参数 目标地址
            // 第二个参数 具体的数据信息
            // 第三个参数 传送数据的格式
            // 第四个参数 优先级 0~9 0~4普通消息 5~9 加急消息
            // 第五个参数 消息的过期时间 过期默认消费
            producer.send(queue, msg, DeliveryMode.NON_PERSISTENT, i,1000*60L);
            TimeUnit.SECONDS.sleep(1);
        }
        //如果支持事物，session必须commit 小量提交最好
        //session.commit();
        session.close();
        connection.close();

    }
}
