package club.gsjblog.springcloud.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  天气预报 basic
 */
@SpringBootApplication
@EnableEurekaClient
public class WeatherWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WeatherWebApplication.class).web(true).run(args);
    }
}
