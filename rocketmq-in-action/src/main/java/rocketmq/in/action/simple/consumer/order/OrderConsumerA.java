package rocketmq.in.action.simple.consumer.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

public class OrderConsumerA {

    static String namesrvAddr = "localhost:9876";

    static String ORDER_CONSUMER = "order_consumer_a";

    public static void main(String[] args) throws InterruptedException, MQClientException {

        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(ORDER_CONSUMER);

        // 设置NameServer的地址
        consumer.setNamesrvAddr(namesrvAddr);

        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe("order_message", "*");
        // 注册回调实现类来处理从broker拉取回来的消息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.printf("%s  listener 1 Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msg.getBody()));
            }

            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.printf("%s listener 2 Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msg.getBody()));
            }

            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });


        // 启动消费者实例
        consumer.start();
        System.out.printf("Order Consumer Started.%n");
    }
}
