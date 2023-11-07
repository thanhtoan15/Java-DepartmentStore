package com.raven.service;

import com.raven.model.ModelMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceMail {

    public ModelMessage sendMain(String toEmail, String code) {
        
        ModelMessage ms = new ModelMessage(false, "");
        String from = "VinaFood";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        String username = "hokietvpn@gmail.com";
        String password = "feoitjhcdonngbsi";    //  Your email password here
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("VinaFood - Mã xác minh");
            message.setText(code + " - Vui lòng không chia sẻ mã xác minh này với bất kì ai !");
            Transport.send(message);
            System.out.println("Đã gửi mã xác minh thành công !");
            ms.setSuccess(true);
        } catch (MessagingException e) {
//            e.printStackTrace();
//            System.out.println(""+e.getMessage());
        
            if (e.getMessage().equals("Illegal address")) {
                ms.setMessage("Email không hợp lệ");
            } else {
                ms.setMessage("kiểm tra lại email");
            }
        }
        return ms;
    }
}
