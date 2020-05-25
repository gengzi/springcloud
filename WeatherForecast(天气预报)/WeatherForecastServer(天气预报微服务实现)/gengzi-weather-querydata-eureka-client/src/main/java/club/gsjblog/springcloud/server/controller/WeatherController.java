package club.gsjblog.springcloud.server.controller;

import club.gsjblog.springcloud.server.bean.WeatherResponse;
import club.gsjblog.springcloud.server.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 天气预报controller
 *
 * @date 2018年12月2日12:49:01
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * 根据城市名称，查询天气信息
     *
     * @param cityname 城市名称
     * @return WeatherResponse
     */
    @RequestMapping(value = "/cityName/{cityname}", method = RequestMethod.GET)
    public WeatherResponse getWeatherByCityName(@PathVariable("cityname") String cityname) {
        return weatherService.getWeatherByCityName(cityname);
    }


    /**
     * 根据城市id，查询天气信息
     *
     * @param cityId 城市id
     * @return WeatherResponse
     */
    @RequestMapping(value = "/cityId/{cityId}", method = RequestMethod.GET)
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId) {
        return weatherService.getWeatherByCityId(cityId);
    }


}
