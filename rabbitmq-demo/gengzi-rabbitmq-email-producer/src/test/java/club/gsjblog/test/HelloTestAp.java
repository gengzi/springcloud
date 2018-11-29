package club.gsjblog.test;

import club.gsjblog.springcloud.server.RabbitmqApplication;
import club.gsjblog.springcloud.server.rabbitmq_springboot.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/11/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RabbitmqApplication.class)
public class HelloTestAp {


    @Resource
    private Sender sender;

    @Test
    public void hello(){
        sender.send();
    }




}
