package club.gsjblog.springcloud.server.controller;

import club.gsjblog.springcloud.server.bean.CityList;
import club.gsjblog.springcloud.server.service.CitydataListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 天气预报controller
 *
 * @date 2018年11月27日22:52:53
 */
@RestController
@RequestMapping("city")
public class CityDataController {


    @Autowired
    private CitydataListService citydataListService;

    /**
     * 城市列表
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CityList getWeatherByCityName() {
        try {
            return citydataListService.loadCityListXml();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
