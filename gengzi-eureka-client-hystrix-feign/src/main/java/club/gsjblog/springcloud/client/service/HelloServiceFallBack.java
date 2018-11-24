package club.gsjblog.springcloud.client.service;

import club.gsjblog.springcloud.client.bean.Users;
import org.springframework.stereotype.Component;

/**
 * feign 提供的服务降级实现
 *  需要实现 feign 客户端
 *  需要在 feign 设置一个注解
 *  @FeignClient(name="provider-service",fallback = HelloServiceFallBack.clas
 */
@Component
public class HelloServiceFallBack  implements  HelloService{

    @Override
    public String hello() {
        return "erro";
    }

    @Override
    public String getTest() {
        return "erro";
    }

    @Override
    public String getTestParm(String name) {
        return "erro";
    }

    @Override
    public String getTestUser(String name, Integer age) {
        return "erro";
    }

    @Override
    public String postTestUser(Users user) {
        return "erro";
    }

    @Override
    public String helloTimeouts() {
        return "erro";
    }
}
