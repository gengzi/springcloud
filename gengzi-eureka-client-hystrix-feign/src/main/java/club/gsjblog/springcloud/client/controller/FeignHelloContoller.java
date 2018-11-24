package club.gsjblog.springcloud.client.controller;

import club.gsjblog.springcloud.client.bean.Users;
import club.gsjblog.springcloud.client.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  feign 的服务调用
 */
@RestController
public class FeignHelloContoller {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/feignhello")
    public String feignHelloConsumer(){
        //调用其它微服务
        String hello = helloService.hello();
        //处理你的业务 ....
        hello = "使用声明式的服务调用:"+hello;
        return hello;
    }

    @RequestMapping("/feign/gettest")
    public String feignGetTest(){
        //调用其它微服务
        String result = helloService.getTest();
        //处理你的业务 ....
        result = "使用声明式的服务调用:"+result;
        return result;
    }

    @RequestMapping("/feign/gettest/param")
    public String feignGetTestParam(){
        //调用其它微服务
        String name = "张三";
        String result = helloService.getTestParm(name);
        //处理你的业务 ....
        result = "使用声明式的服务调用:"+result;
        return result;
    }


    @RequestMapping("/feign/gettest/user")
    public String feignGetTestUser(){
        //调用其它微服务
        String name = "张三";
        String result = helloService.getTestUser(name,22);
        //处理你的业务 ....
        result = "使用声明式的服务调用:"+result;
        return result;
    }


    @RequestMapping("/feign/gettest/postuser")
    public String feignPostTestUser(){
        //调用其它微服务
        Users user = new Users();
        user.setName("李四");
        user.setAge(23);
        String result = helloService.postTestUser(user);
        //处理你的业务 ....
        result = "使用声明式的服务调用:"+result;
        return result;
    }


    @RequestMapping("/feign/gettest/all")
    public String feignAll(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(helloService.getTest()).append("\n");
        stringBuilder.append(helloService.getTestParm("张三")).append("\n");

        stringBuilder.append(helloService.getTestUser("张三",22)).append("\n");
        Users user = new Users();
        user.setName("李四");
        user.setAge(23);
        stringBuilder.append(helloService.postTestUser(user)).append("\n");
        return stringBuilder.toString();
    }


    // ============================== 演示重试机制 有问题，触发了重试，但是结果不响应，依然走熔断

    /**
     *
     * 注意  Feigen 会将所有的 Feigen 客户端方法都封装到
     * hystrix 命令中进行服务保护。
     *
     *
     */

    @RequestMapping("/feignhellotimeouts")
//    @HystrixCommand(fallbackMethod = "helloFallBack")  // 配置在这的 fallback 比 feign 提供的熔断 级别高
    public String feignHelloTimeouts(){
        //调用其它微服务
        String hello = helloService.helloTimeouts();

        long after = System.currentTimeMillis();
        //处理你的业务 ....
        hello = "使用声明式的服务调用:"+hello;
        return hello;
    }

    public String helloFallBack(){
            return  "熔断";
    }




}
