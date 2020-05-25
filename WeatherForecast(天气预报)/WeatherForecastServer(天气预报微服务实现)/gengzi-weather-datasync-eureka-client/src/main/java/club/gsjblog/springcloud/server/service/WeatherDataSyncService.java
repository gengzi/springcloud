package club.gsjblog.springcloud.server.service;

/**
 * 天气数据同步
 */
public interface WeatherDataSyncService {

    /**
     * 同步天气数据，根据cityid查询第三方接口
     * @param cityId
     */
    void syncDateByCityId(String cityId);

}
