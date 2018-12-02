package club.gsjblog.springcloud.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
/**
 *  天气预报 basic
 */
@SpringBootApplication
public class WeatherWebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WeatherWebApplication.class).web(true).run(args);
    }
}
