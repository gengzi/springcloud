package club.gsjblog.springcloud.server.service.impl;

import club.gsjblog.springcloud.server.bean.WeatherResponse;
import club.gsjblog.springcloud.server.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;

/**
 * 天气服务service 的实现类
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    //导入日志
    Logger logger =  LoggerFactory.getLogger(WeatherServiceImpl.class);
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
     * 根据城市名称，查询天气信息
     *
     * @param cityname 城市名称
     * @return WeatherResponse
     */
    @Override
    public WeatherResponse getWeatherByCityName(String cityname) {
        return getWeatherResponse("?city=" + cityname);
    }


    /**
     * 根据城市id，查询天气信息
     *
     * @param cityId 城市id
     * @return WeatherResponse
     */
    @Override
    public WeatherResponse getWeatherByCityId(String cityId) {
        return getWeatherResponse("?citykey=" + cityId);
    }


    private WeatherResponse getWeatherResponse(String citycode) {

        //天气数据的特点，基本半小时更新一次数据。
        //根据citycode 或者 cityid 查询redis中是否存在
        //存在，从Redis中获取数据
        //不存在，调用第三方服务,并写入缓存
        String weatherUri = publicWeatherUri + citycode;
        System.out.println(weatherUri);
        String body = null;
        ObjectMapper obj = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.hasKey(citycode)) {
            logger.info("读缓存");
            body = stringStringValueOperations.get(citycode);
        } else {
            ResponseEntity<String> forEntity = restTemplate.getForEntity(weatherUri, String.class);
            if (forEntity.getStatusCodeValue() == 200) {
                logger.info("调用接口");
                body = forEntity.getBody();
                //10毫秒过期
                stringStringValueOperations.set(citycode, body,TIMEOUT, TimeUnit.SECONDS);
            }
        }
        try {
            weatherResponse = obj.readValue(body, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }


}
