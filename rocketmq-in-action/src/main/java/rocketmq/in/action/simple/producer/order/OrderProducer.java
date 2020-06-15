package rocketmq.in.action.simple.producer.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 订单消息生产者
 */
public class OrderProducer {
    static String namesrvAddr = "localhost:9876";
    private static String ORDER_PRODUCER = "order_producer";

    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer(ORDER_PRODUCER);
        // 设置NameServer的地址

        producer.setNamesrvAddr(namesrvAddr);
        // 启动Producer实例
        producer.start();

        // 创建消息，并指定Topic，Tag和消息体
        for (int i = 0; i < 10; i++) {
            {
                Message msg = new Message("order_message" /* Topic */,
                        "order_1" /* Tag */,
                        ("This is order message. tags: [order_1]. order id is:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );

                // 发送消息到一个Broker
                SendResult sendResult = producer.send(msg);
                // 通过sendResult返回消息是否成功送达
                System.out.printf("%s%n", sendResult);
            }
            {
                Message msg = new Message("order_message" /* Topic */,
                        "order_2" /* Tag */,
                        ("This is order message. tags: [order_1]. order id is:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );

                // 发送消息到一个Broker
                //SendResult sendResult = producer.send(msg);
                // 通过sendResult返回消息是否成功送达
                //System.out.printf("%s%n", sendResult);
            }

        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
