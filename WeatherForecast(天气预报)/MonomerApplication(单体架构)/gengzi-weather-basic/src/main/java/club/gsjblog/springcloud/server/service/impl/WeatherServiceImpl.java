package club.gsjblog.springcloud.server.service.impl;

import club.gsjblog.springcloud.server.bean.WeatherResponse;
import club.gsjblog.springcloud.server.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 天气服务service 的实现类
 */
@Service
public class WeatherServiceImpl  implements WeatherService{

    @Value(value = "${weather.uri}")
    private String  publicWeatherUri;

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
        return getWeatherResponse("?city="+cityname);
    }


    /**
     * 根据城市id，查询天气信息
     *
     * @param cityId 城市id
     * @return WeatherResponse
     */
    @Override
    public WeatherResponse getWeatherByCityId(String cityId) {
        return getWeatherResponse("?citykey="+cityId);
    }


    private WeatherResponse getWeatherResponse(String citycode) {
        String weatherUri = publicWeatherUri+ citycode;
        System.out.println(weatherUri);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(weatherUri, String.class);
        String body = null;
        ObjectMapper obj = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        if (forEntity.getStatusCodeValue() == 200){
            body = forEntity.getBody();
        }
        try {
            weatherResponse = obj.readValue(body, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }
}
