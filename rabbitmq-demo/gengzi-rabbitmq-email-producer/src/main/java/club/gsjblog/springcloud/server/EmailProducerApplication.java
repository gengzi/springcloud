package club.gsjblog.springcloud.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 *  注册中心启动类
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value = "club.gsjblog.springcloud.server")
public class EmailProducerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EmailProducerApplication.class).web(true).run(args);
    }
}
