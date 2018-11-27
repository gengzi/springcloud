package club.gsjblog.springcloud.server.rabbitmq_springboot;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/27.
 *  消息的生产者
 *
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 生产消息
     */
    public void send(){
        String content = "hello"+new Date();
        System.out.println("sneder:"+content);
        this.rabbitTemplate.convertAndSend("hello",content);
    }


}
