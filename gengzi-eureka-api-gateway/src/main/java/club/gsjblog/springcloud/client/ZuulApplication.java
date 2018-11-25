package club.gsjblog.springcloud.client;

import club.gsjblog.springcloud.client.filter.AccessFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


/**
 *  网关启动类
 *  网关出现的原因： 对于运维来说，难以维护配置 各个服务节点
 *  对于开发来说，调用的接口往往需要加一定的权限校验，
 *  在之前我们的做法会在项目中加filter 来处理，那么微服务架构中
 *  包含很多服务，每个服务实现一套，会造成代码冗余，
 *  那一般我们会建立一个专门的鉴权服务，每次先调用鉴权服务，再走
 *  我们写好的接口，但是鉴权服务会入侵到微服务的应用中。
 *  所以采用了，前置的网关服务来完成一些非业务的校验，那这些校验
 *  跟微服务的业务无关。
 *
 *
 */
@EnableZuulProxy    //  开启 zuul 的API 网关服务功能
@SpringBootApplication
public class ZuulApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }


    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

}
