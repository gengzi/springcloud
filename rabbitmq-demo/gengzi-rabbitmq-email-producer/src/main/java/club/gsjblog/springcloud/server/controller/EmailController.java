package club.gsjblog.springcloud.server.controller;

import club.gsjblog.springcloud.server.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/11/29.
 */
@RestController
public class EmailController {


    @Autowired
    private EmailService service;

    /**
     * 发送邮件
     * @return
     */
    @RequestMapping("/send")
    public String sendEmail(@RequestParam("info") String info){
        return  service.sendEmail(info);
    }


}
