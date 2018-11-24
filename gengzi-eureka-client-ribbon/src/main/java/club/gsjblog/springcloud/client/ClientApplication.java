package club.gsjblog.springcloud.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *  微服务启动类
 *
 *  eureka 分为 sever 端和 client 端， server端是注册中心，client端是 服务提供者或者服务消费者
 *  因为通过一个微服务，即是服务提供者 又是 服务消费者
 *
 *
 */
//@EnableEurekaClient
@EnableDiscoveryClient
//@EnableCircuitBreaker         //客户端的负载均衡
@SpringBootApplication
public class ClientApplication {


    /**
     *   两种实现负载均衡的方式：
         服务端负载均衡 和 客户端负载均衡
         使用 nginx 的反向代理达到负载均衡，是服务端负载均衡
         使用 ribbon 中 loadbalancer 的负载策略，如 轮询，随机，响应时间加权 等，是客户端负载均衡
     */

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
        new SpringApplicationBuilder(ClientApplication.class).web(true).run(args);
    }
}
