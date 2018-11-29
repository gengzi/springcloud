package club.gsjblog.springcloud.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
/**
 *  天气预报 basic
 */
@SpringBootApplication
public class WeatherApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WeatherApplication.class).web(true).run(args);
    }
}
