package club.gsjblog.springcloud.client.controller;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloConsumer(){

        /**
         *  restTemplate 有三种构造方法
         */
        return restTemplate.getForEntity("http://PROVIDER-SERVICE/hello",String.class).getBody();
    }

    /**
     * 调用hello 服务失败后执行
     * @return
     */
    public String helloFallBack(){

        return "服务调用失败，请稍后再试";
    }


//    ===================================

    /**
     * 耗时操作，断路器
     * @return
     */
    @RequestMapping(value = "/ribben-consumer/time")
    @HystrixCommand(fallbackMethod = "timeoutFallBack")
    public String timeoutService(){
        long before = System.currentTimeMillis();
        String body = restTemplate.getForEntity("http://PROVIDER-SERVICE/hello/timeouts", String.class).getBody();
        long after = System.currentTimeMillis();
        System.out.println("耗时"+(after-before));
        System.out.println(body);
        return body;
    }


    /**
     * 发送超时错误
     * @return
     */
    public  String timeoutFallBack(){
        return "timeouts";
    }





}
