package club.gsjblog.springcloud.server.rabbitmq_springboot;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/11/27.
 * 消费者
 *
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    /**
     * 处理请求
     * @param hello
     */
    @RabbitHandler
    public void process(String hello){
        System.out.println("receiver"+hello);
    }

}
