package club.gsjblog.springcloud.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  天气预报 basic
 */
@SpringBootApplication
@EnableEurekaClient  //引入 eureka-client
public class WeatherQueryDataApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WeatherQueryDataApplication.class).web(true).run(args);
    }
}
