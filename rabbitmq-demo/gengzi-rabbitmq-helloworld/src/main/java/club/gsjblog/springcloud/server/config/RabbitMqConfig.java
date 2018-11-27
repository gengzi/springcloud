package club.gsjblog.springcloud.server.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Administrator on 2018/11/27.
 * spring boot  整合 RabbitMq  java  配置   最简单化
 *
 */
@Configuration
public class RabbitMqConfig {


    /**
     *
     * @return
     */
    @Bean
    public Queue queue(){

        return new Queue("hello");
    }

}
