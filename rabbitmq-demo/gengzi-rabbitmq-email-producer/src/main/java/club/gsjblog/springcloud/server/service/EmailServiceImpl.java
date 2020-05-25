package club.gsjblog.springcloud.server.service;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/11/29.
 */
@Service
public class EmailServiceImpl implements  EmailService{


    // rabbitmq 的实例
    @Autowired
    @Qualifier(value = "rabbitTemplate")
    private RabbitTemplate rabbitTemplate;


    @Value("${mq.exchange.name}")
    private String exchange;

    @Value("${mq.routekey.name}")
    private String routeKey;


    /**
     * 发送邮件
     *
     * @param emailStr 邮件内容
     * @return
     */
    @Override
    public String sendEmail(String emailStr) {

        rabbitTemplate.convertAndSend(exchange, routeKey, emailStr);

        return emailStr;
    }
}
