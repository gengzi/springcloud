package club.gsjblog.springcloud.server.job;

import club.gsjblog.springcloud.server.bean.City;
import club.gsjblog.springcloud.server.bean.CityList;
import club.gsjblog.springcloud.server.service.CitydataListService;
import club.gsjblog.springcloud.server.service.WeatherService;
import club.gsjblog.springcloud.server.service.impl.CityDataListServiceImpl;
import club.gsjblog.springcloud.server.service.impl.WeatherServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.List;

/**
 * 天气预报数据同步
 *
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    @Autowired
    private CitydataListService cityDataListService;

    @Autowired
    private WeatherService weatherService;

    //导入日志
    Logger logger =  LoggerFactory.getLogger(WeatherServiceImpl.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("quartz 定时任务");
        try {
            CityList cityListData = cityDataListService.loadCityListXml();
            List<City> cityList = cityListData.getCityList();
            for (City c : cityList){
                weatherService.getWeatherByCityId(c.getCityId());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
