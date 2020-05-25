package club.gsjblog.springcloud.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  天气数据同步服务
 *  定时同步天气数据，到redis中
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient   //引入 eureka-client
public class WeatherDataSyncApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WeatherDataSyncApplication.class).web(true).run(args);
    }
}
