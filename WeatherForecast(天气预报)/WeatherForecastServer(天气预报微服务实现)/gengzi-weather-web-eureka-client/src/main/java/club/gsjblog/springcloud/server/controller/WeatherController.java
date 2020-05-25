package club.gsjblog.springcloud.server.controller;

import club.gsjblog.springcloud.server.bean.City;
import club.gsjblog.springcloud.server.bean.Weather;
import club.gsjblog.springcloud.server.bean.WeatherResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 天气预报controller
 *
 * @date 2018年11月27日22:52:53
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

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

        // TODO citylist 调用城市数据api服务
            City city = new City();
            city.setCityId("101010100");
            city.setCityName("北京");
            ArrayList<City> cityList = new ArrayList<>();
            cityList.add(city);
        model.addAttribute("cityList",cityList); // 城市列表

        // TODO  weather数据，由天气数据api服务提供

        WeatherResponse weatherResponse = new WeatherResponse();
        Weather weather = new Weather();
        weather.setCity("北京");
        weather.setWendu("22");
        weatherResponse.setData(weather);

        model.addAttribute("cityWeather",weatherResponse);  //城市天气数据
        return new ModelAndView("weather/report","reportModel",model);
    }


}
