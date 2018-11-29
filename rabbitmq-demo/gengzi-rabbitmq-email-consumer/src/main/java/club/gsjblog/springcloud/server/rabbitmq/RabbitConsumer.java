//package club.gsjblog.springcloud.server.rabbitmq;
//
//import com.rabbitmq.client.*;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
///**
// *
// * 消费者
// *
// * Created by Administrator on 2018/11/26.
// */
//public class RabbitConsumer {
//
//    private static final String QUEUE_NAME = "queue-demo";  // 队列
//    //服务ip
//    private static final String IP_ADDRESS = "127.0.0.1";
//    //端口号
//    private static final Integer PORT = 5672;
//
//    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//        Address[] addresses = new Address[]{
//                new Address(IP_ADDRESS,PORT)
//        };
//
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//
//        connectionFactory.setUsername("gengzi");
//        connectionFactory.setPassword("gengzi");
//
//        Connection connection = connectionFactory.newConnection(addresses);//创建连接
//        final Channel channel = connection.createChannel(); //创建信道
//        // 设置消费者最大能“保持”的未确认消息数。
//        channel.basicQos(64);
//
//        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
//
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//
//                System.out.println("message:" + new String(body));
//
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                channel.basicAck(envelope.getDeliveryTag(), false);
//            }
//        };
//
//        channel.basicConsume(QUEUE_NAME,defaultConsumer);
//
//        TimeUnit.SECONDS.sleep(5);
//
//        channel.close();
//        connection.close();
//
//
//    }
//
//
//
//}
