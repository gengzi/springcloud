package club.gsjblog.springcloud.server.service.impl;

import club.gsjblog.springcloud.server.service.WeatherDataSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataSyncServiceImpl implements WeatherDataSyncService {

    //导入日志
    Logger logger =  LoggerFactory.getLogger(WeatherDataSyncServiceImpl.class);
    //超时时间
    private static final long TIMEOUT = 1800L;

    @Value(value = "${weather.uri}")
    private String publicWeatherUri;


    //Spring dtat redis 提供的对String 类型提供的
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestTemplate restTemplate;  // Spring 中对 Httpclient 的封装

    /**
     * 同步天气数据，根据cityid查询第三方接口
     *
     * @param cityId
     */
    @Override
    public void syncDateByCityId(String cityId) {
        String weatherUri = publicWeatherUri + "?citykey=" + cityId;
        logger.info("正在同步city:"+cityId);
        this.saveWeatherData(weatherUri);
    }


    /**
     * 把天气数据放在缓存
     * @param uri
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        // 调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        // 数据写入缓存
        ops.set(key, strBody, TIMEOUT, TimeUnit.SECONDS);

    }
}
