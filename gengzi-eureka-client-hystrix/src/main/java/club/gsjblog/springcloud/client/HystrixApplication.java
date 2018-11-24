package club.gsjblog.springcloud.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *  微服务启动类
 */
@EnableEurekaClient
@EnableCircuitBreaker         //使用断路器，hystrix
@SpringBootApplication
public class HystrixApplication {


    /**
     *  loadbalanced 开启客户端负载
     *  @Bean 相当于  RestTemplate restTemplate = new RestTemplate()
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){

        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixApplication.class).web(true).run(args);
    }
}
