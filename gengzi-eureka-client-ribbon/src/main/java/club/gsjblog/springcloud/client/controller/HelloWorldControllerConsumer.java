package club.gsjblog.springcloud.client.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *  简单的服务消费者示例
 *  @author gengzi
 *  2018年11月23日23:56:19
 *
 */
@RestController
public class HelloWorldControllerConsumer {

    // RestTemplate 是 spring cloud 提供的对服务提供者的请求
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/ribben-consumer")
    public String helloConsumer(){

        /**
         *  restTemplate 有三种构造方法
         */
        return restTemplate.getForEntity("http://PROVIDER-SERVICE/hello",String.class).getBody();
    }


}
