package club.gsjblog.springcloud.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
/**
 *  天气数据同步服务
 *  定时同步天气数据，到redis中
 *
 *
 */
@SpringBootApplication
public class WeatherDataSyncApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WeatherDataSyncApplication.class).web(true).run(args);
    }
}
