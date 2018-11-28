package club.gsjblog.springcloud.server.service;

import club.gsjblog.springcloud.server.bean.WeatherResponse;

/**
 *  天气服务调用接口
 */
public interface WeatherService {

    /**
     * 根据城市名称，查询天气信息
     * @param cityname  城市名称
     * @return WeatherResponse
     */
    WeatherResponse  getWeatherByCityName(String cityname);


    /**
     * 根据城市id，查询天气信息
     * @param cityId  城市id
     * @return  WeatherResponse
     */
    WeatherResponse getWeatherByCityId(String cityId);





}
