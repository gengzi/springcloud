package club.gsjblog.springcloud.server.service;

import club.gsjblog.springcloud.server.bean.CityList;

import java.io.IOException;

/**
 * 读取所有的城市列表
 */
public interface CitydataListService {

   CityList loadCityListXml() throws IOException;

}
