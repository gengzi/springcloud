package club.gsjblog.springcloud.server.config;

import club.gsjblog.springcloud.server.listener.MailMessageListenerAdapter;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


/**
 * Created by Administrator on 2018/11/27.
 * spring boot  整合 RabbitMq  java  配置   最简单化
 *
 */
@Configuration
@PropertySource(value = {"classpath:application.yml"})
public class RabbitMqConfig {


    @Autowired
    private Environment env;


    /**
     *  创建 rabbitmq 的连接工厂
     * @return
     * @throws Exception
     */
    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(env.getProperty("spring.rabbitmq.host").trim());
        connectionFactory.setPort(Integer.parseInt(env.getProperty("spring.rabbitmq.port").trim()));
        connectionFactory.setVirtualHost(env.getProperty("spring.rabbitmq.virtual-host").trim());
        connectionFactory.setUsername(env.getProperty("spring.rabbitmq.username").trim());
        connectionFactory.setPassword(env.getProperty("spring.rabbitmq.password").trim());
        return connectionFactory;
    }


    @Bean
    public CachingConnectionFactory cachingConnectionFactory() throws Exception {
        return new CachingConnectionFactory(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws Exception {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
        rabbitTemplate.setChannelTransacted(true);
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin() throws Exception {
        return new RabbitAdmin(cachingConnectionFactory());
    }


    /**
     * ，它是一个监听器容器，用来监听消息队列进行消息处理的。
     * 注意的是，我们这里设置手动 ACK 的方式。默认的情况下，它采用自动应答，这种方式中消息队列会发送消息后立即从消息队列中删除该消息。
     * 此时，我们通过手动 ACK 方式，如果消费者因宕机或链接失败等原因没有发送 ACK，RabbitMQ
     * 会将消息重新发送给其他监听在队列的下一个消费者，保证消息的可靠性。
     * @param mailMessageListenerAdapter    监听器
     * @return
     * @throws Exception 
     */
    @Bean
    public SimpleMessageListenerContainer listenerContainer(
            @Qualifier("mailMessageListenerAdapter") MailMessageListenerAdapter mailMessageListenerAdapter) throws Exception {
        String queueName = env.getProperty("mq.queue.name").trim();
        System.out.println(queueName);

        SimpleMessageListenerContainer simpleMessageListenerContainer =
                new SimpleMessageListenerContainer(cachingConnectionFactory());
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(mailMessageListenerAdapter);
        // 设置手动 ACK
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return simpleMessageListenerContainer;
    }
}
