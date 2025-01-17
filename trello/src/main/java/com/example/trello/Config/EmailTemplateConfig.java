package com.example.trello.Config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailTemplateConfig {
    
    @Value("${spring.mail.host}")
    String host;

    @Value("${spring.mail.port}")
    Integer port;

    @Value("${spring.mail.fromEmail}")
    String fromEmail;

    @Value("${spring.mail.password}")
    String password;
    
    @Bean
    public JavaMailSender getJavaMailSender() {
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("duong2lophot@gmail.com");
        mailSender.setPassword("qnms tljn vttb xzsh");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
}
