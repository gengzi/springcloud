package club.gsjblog.springcloud.server.service;

/**
 * Created by Administrator on 2018/11/29.
 */
public interface EmailService {


    /**
     * 发送邮件
     * @param emailStr  邮件内容
     * @return
     */
    String sendEmail(String emailStr);


}
