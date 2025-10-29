package com.xworkz.news.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailSent {


    @Autowired
    private JavaMailSender emailSender;


    private String otpGeneretor(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    
    public String mailSend(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("amith.s.xworkz@gmail.com");
        message.setTo(email);
        message.setSubject("Forgot Password Otp");
        String generatedOtp=otpGeneretor();
        message.setText("Your one time password(OTP) is : "+ generatedOtp);
        emailSender.send(message);
        return generatedOtp;
    }

    public String mimeMessage(String email) {

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"UTF-8");
            helper.setFrom("amith.s.xworkz@gmail.com");
            helper.addTo(email);
            helper.setSubject("News App Registration");
            String content = "<html>" +
                    "<body>" +
                    "<p>Your registration was completed successfully!</p>" +
                    "</body>" +
                    "</html>";

            helper.setText(content,true);
        };
        emailSender.send(preparator);

        return "Mail sent successfully";

    }

}
