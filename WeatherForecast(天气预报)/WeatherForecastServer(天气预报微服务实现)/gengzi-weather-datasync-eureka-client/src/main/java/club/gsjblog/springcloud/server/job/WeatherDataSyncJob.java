package club.gsjblog.springcloud.server.job;

import club.gsjblog.springcloud.server.bean.City;
import club.gsjblog.springcloud.server.service.WeatherDataSyncService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 天气预报数据同步
 *
 */
public class WeatherDataSyncJob extends QuartzJobBean {
    //导入日志
    Logger logger =  LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private WeatherDataSyncService weatherService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("quartz 定时任务");
        // TODO citylist 调用城市数据api服务
            City city = new City();
            city.setCityId("101010100");
            city.setCityName("北京");
            ArrayList<City> cityList = new ArrayList<>();
            cityList.add(city);

        for (City c : cityList){
            weatherService.syncDateByCityId(c.getCityId());
        }
    }
}
