package club.gsjblog.springcloud.server.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * 生产消息
 * Created by Administrator on 2018/11/26.
 */
public class RabbitProduce {


    private static final String EXCHANGE_NAME = "exchange-demo";

    private static final String ROUNTING_KEY = "routingkey-demo";

    private static final String QUEUE_NAME = "queue-demo";
    //服务ip
    private static final String IP_ADDRESS = "127.0.0.1";
    //端口号
    private static final Integer port = 5672;


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(port);
        connectionFactory.setUsername("gengzi");
        connectionFactory.setPassword("gengzi");

        Connection connection = connectionFactory.newConnection(); //创建连接
        Channel channel = connection.createChannel(); //创建信道
        //创建一个 type="direct"、持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建一个持久化、非拍他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUNTING_KEY);

        //发送一条持久化的消息 hello world
        String message = "hello world";
        channel.basicPublish(EXCHANGE_NAME,ROUNTING_KEY,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

        channel.close();
        connection.close();
    }






}
