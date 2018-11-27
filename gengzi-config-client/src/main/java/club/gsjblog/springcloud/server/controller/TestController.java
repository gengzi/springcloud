package club.gsjblog.springcloud.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/11/27.
 */
@RestController
public class TestController {


    @Value("${from}")   //读取服务端的配置   gengzispace.yml  文件
    private String from;

    @RequestMapping("from")
    public String from(){
        return this.from;
    }


}
