package club.gsjblog.springcloud.server.config;

import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


/**
 * Created by Administrator on 2018/11/27.
 * spring boot  整合 RabbitMq  java  配置
 *
 */
@Configuration
@ComponentScan(value = "club.gsjblog.springcloud.server")
@PropertySource({"classpath:application.yml","classpath:rabbitmq.yml"})   //读取配置文件
public class RabbitMqConfig {

    /**
     *  拿到配置环境
     */
    @Autowired
    private Environment env;




    /**
     *  创建 rabbitmq 的连接工厂
     *   ConnectionFactory connectionFactory = new ConnectionFactory();
     * @return
     * @throws Exception
     */
    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        //连接rabbitmq connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置主机地址
        connectionFactory.setHost(env.getProperty("spring.rabbitmq.host").trim());
        //设置端口号
        connectionFactory.setPort(Integer.parseInt(env.getProperty("spring.rabbitmq.port").trim()));
        //设置虚拟地址
        connectionFactory.setVirtualHost(env.getProperty("spring.rabbitmq.virtual-host").trim());
        //设置用户名
        connectionFactory.setUsername(env.getProperty("spring.rabbitmq.username").trim());
        //设置密码
        connectionFactory.setPassword(env.getProperty("spring.rabbitmq.password").trim());
        //Connection conn =     connectionFactory.newConnection();  // 创建连接
        //Channel  channel =  conn.newChannel();   //创建信道 channel 用来发送或者接收信息
        return connectionFactory;
    }


    /**
     *  缓存
     * @return
     * @throws Exception
     */
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
     *  交换器（exchange）和 队列（queue） 是 high-level 层面构建模块，确保要在使用的时候，已经存在了
     * @return
     */
    @Bean
    public Queue queue(){
        String name = env.getProperty("mq.queue.name").trim();
        // 是否持久化
        boolean durable = StringUtils.isNotBlank(env.getProperty("mq.queue.durable").trim())?
                Boolean.valueOf(env.getProperty("mq.queue.durable").trim()) : true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = StringUtils.isNotBlank(env.getProperty("mq.queue.exclusive").trim())?
                Boolean.valueOf(env.getProperty("mq.queue.exclusive").trim()) : false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(env.getProperty("mq.queue.autoDelete").trim())?
                Boolean.valueOf(env.getProperty("mq.queue.autoDelete").trim()) : false;
        return new Queue(name, durable, exclusive, autoDelete);
    }

    /**
     * 交换器（exchange）和 队列（queue） 是 high-level 层面构建模块，确保要在使用的时候，已经存在了
     * @return
     */
    @Bean
    TopicExchange exchange() {
        String name = env.getProperty("mq.exchange.name").trim();
        // 是否持久化
        boolean durable = StringUtils.isNotBlank(env.getProperty("mq.exchange.durable").trim())?
                Boolean.valueOf(env.getProperty("mq.exchange.durable").trim()) : true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(env.getProperty("mq.exchange.autoDelete").trim())?
                Boolean.valueOf(env.getProperty("mq.exchange.autoDelete").trim()) : false;
        //使用了 topicExchange  ,在匹配bingdkey  和 routing key 会更方便         routekey  :email_routekey
        return new TopicExchange(name, durable, autoDelete);
    }

    @Bean
    Binding binding() {
        String routekey = env.getProperty("mq.routekey.name").trim();
        return BindingBuilder.bind(queue()).to(exchange()).with(routekey);
    }

}
