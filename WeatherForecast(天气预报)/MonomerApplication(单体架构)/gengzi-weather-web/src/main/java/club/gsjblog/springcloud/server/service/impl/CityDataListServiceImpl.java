package club.gsjblog.springcloud.server.service.impl;

import club.gsjblog.springcloud.server.bean.CityList;
import club.gsjblog.springcloud.server.service.CitydataListService;
import club.gsjblog.springcloud.server.utils.XmlBuilder;
import org.mockito.internal.configuration.ClassPathLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CityDataListServiceImpl implements CitydataListService {


    @Override
    public CityList loadCityListXml() throws IOException {

        ClassPathResource classPathResource = new ClassPathResource("citylist.xml");
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null ){
                    stringBuffer.append(line);
            }
            return (CityList) XmlBuilder.xmlStrToOject(CityList.class, stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                reader.close();
            }
        }
        return null;
    }
}
