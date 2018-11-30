package club.gsjblog.springcloud.server.listener;

import club.gsjblog.springcloud.server.bean.MailMessageModel;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Administrator on 2018/11/30.
 */
@Component("mailMessageListenerAdapter")
public class MailMessageListenerAdapter  extends MessageListenerAdapter{

    @Resource
    private JavaMailSender mailSender;

    @Value("${mail.username}")
    private String mailUsername;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        try{
            //解析rabbitmq 的消息体
            String emailBean = new String(message.getBody());
            System.out.println("json" + emailBean);

            MailMessageModel mailMessageModel = JSONObject.toJavaObject(JSONObject.parseObject(emailBean), MailMessageModel.class);
            //发送
            sendEmail(mailMessageModel);
            //手动ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendEmail(MailMessageModel mailMessageModel) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse("17839166574@163.com"));
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(mailUsername);
        mimeMessageHelper.setTo(mailMessageModel.getTo());
        mimeMessageHelper.setSubject(mailMessageModel.getSubject());
        mimeMessageHelper.setText(mailMessageModel.getText(), true);
        // 发送邮件
        mailSender.send(mimeMessage);
    }
}
