package club.gsjblog.springcloud.client.service;

import club.gsjblog.springcloud.client.bean.Users;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用feign 声明式服务调用
 *
 * FeignClient设置serviceid 需要调用的服务 provider-service
 * 在别的版本， serviceid  已经没有了
 */
@FeignClient(name="provider-service",fallback = HelloServiceFallBack.class)
public interface HelloService {

    /**
     *  feign 扩展了对 spring mvc 的支持
     *  调用的是  provider-service 的 hello 路径的方法。需要启动
     * @return
     */
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello();


    /**
     * 服务调用
     * @return
     */
    @RequestMapping(value = "/gettest",method = RequestMethod.GET)
    String getTest();


    /**
     * 注意1：
     *  使用各个参数进行参数绑定时，@RequestParam @RequestHeader @PathVariable
     *  必须指定参数名称的注解
     *  如：@RequestParam("name")   必须要指定value
     *
     *  如果不指定，会在启动的时候报：
     *  Caused by: java.lang.IllegalStateException: RequestParam.value() was empty on parameter 0
     *  这个错误
     *
     *   两个坑：1. @GetMapping不支持   2. @PathVariable得设置value
     *
     * 注意2：
     *   只要参数是复杂对象，即使指定了是GET方法，feign依然会以POST方法进行发送请求。
     *
     *
     *
     */

    /**
     * get  携带参数
     * @return
     */
    @RequestMapping(value = "/gettest/param",method = RequestMethod.GET)
    String getTestParm(@RequestParam("name") String name);



    /**
     * get 对象
     * @return
     */
    @RequestMapping(value = "/gettest/user",method = RequestMethod.GET)
    String getTestUser(@RequestParam("name") String name,@RequestParam("age")  Integer age);


    /**
     * post 对象
     * @return
     */
    @RequestMapping(value = "/gettest/postuser",method = RequestMethod.POST)
    String postTestUser(@RequestBody Users user);


    //==================== 演示重试机制，访问一个超时的接口
    /**
     * post 对象
     * @return
     */
    @RequestMapping(value = "/hello/timeouts",method = RequestMethod.GET)
    String helloTimeouts();

}
