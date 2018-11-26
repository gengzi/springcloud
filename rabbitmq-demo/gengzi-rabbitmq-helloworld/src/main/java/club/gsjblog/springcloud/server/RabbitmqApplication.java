package club.gsjblog.springcloud.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
/**
 *  注册中心启动类
 */
@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RabbitmqApplication.class).web(true).run(args);
    }
}
