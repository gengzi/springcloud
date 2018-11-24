package club.gsjblog.springcloud.client.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 *  简单的服务提供者示例
 *  @author gengzi
 *  2018年11月23日23:56:19
 *
 */
@RestController
public class HelloWorldControllerProvider {

    private final Logger logger =  Logger.getLogger(HelloWorldControllerProvider.class);

    @Autowired
    private DiscoveryClient client;

    /**
     * 一般接口
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        logger.info("/hello,host:"+localServiceInstance.getHost()+";service_id:"+localServiceInstance.getServiceId());
        return "hello world";
    }


    /**
     * 请求耗时接口，如果响应时间超过 2000ms 发送错误
     * @return
     */
    @RequestMapping("/hello/timeouts")
    public String timeouts(){
        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        logger.info("/hello,host:"+localServiceInstance.getHost()+";service_id:"+localServiceInstance.getServiceId());

        int random = new Random().nextInt(4000);
        logger.info("耗时"+random);
        try {
            //超时触发断路器， 触发熔断请求
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "no -timeouts";
    }




}
