package club.gsjblog.springcloud.server.controller;

import club.gsjblog.springcloud.server.bean.WeatherResponse;
import club.gsjblog.springcloud.server.service.CitydataListService;
import club.gsjblog.springcloud.server.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 天气预报controller
 *
 * @date 2018年11月27日22:52:53
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private CitydataListService citydataListService;

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


    /**
     * 根据城市id，查询天气信息
     *
     * @param cityId 城市id
     * @return WeatherResponse
     */
    @RequestMapping(value = "/info/{cityId}", method = RequestMethod.GET)
    public ModelAndView getWeatherInfoByCityId(@PathVariable("cityId") String cityId, Model model) throws IOException {


        model.addAttribute("title","天气预报");
        model.addAttribute("cityId",cityId);
        model.addAttribute("cityList",citydataListService.loadCityListXml().getCityList()); // 城市列表
        model.addAttribute("cityWeather",weatherService.getWeatherByCityId(cityId));  //城市天气数据


        return new ModelAndView("weather/report","reportModel",model);
    }


}
