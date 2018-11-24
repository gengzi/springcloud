package club.gsjblog.springcloud.client.controller;

import club.gsjblog.springcloud.client.bean.Users;
import org.springframework.web.bind.annotation.*;

/**
 * feign 测试的controller
 *
 */
@RestController
public class FeignTestController {


    /**
     * 简单的get请求
     * @return
     */
    @RequestMapping(value = "/gettest",method = RequestMethod.GET)
    public String getTest(){
        return "result ： success";
    }


    /**
     * 携带参数的get请求
     * @return
     */
    @RequestMapping(value = "/gettest/param",method = RequestMethod.GET)
    public String getTestByParam(@RequestParam String name){
        return "result ： success"+name;
    }


    /**
     * 携带参数的get请求,传递对象
     * 如果参数写成对象的形式，feigin调用的时候会自动认为是 post请求。
     * 所以需要调用get请求，并且传递对象，需要单个字段传递
     * @return
     */
    @RequestMapping(value = "/gettest/user",method = RequestMethod.GET)
    public String getTestByUser(@RequestParam String name,@RequestParam  Integer age){
        return "result ： success"+name +":"+age;
    }

    /**
     * 携带参数的post请求
     * @return
     */
    @RequestMapping(value = "/gettest/postuser",method = RequestMethod.POST)
    public String postTestByUser(@RequestBody Users user){
        return "result ： success"+user.toString();
    }



}
