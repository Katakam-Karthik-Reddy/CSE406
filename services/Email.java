package org.example.services;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Email {
    public boolean send(String from, String to, String subject, String text, String pathtofile){


        boolean flag = false;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = ""; // your email without @gmail.com
        String password = ""; // your appkey of your email

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);

            BodyPart bodypart = new MimeBodyPart();
            bodypart.setText(text);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodypart);
            String filename = pathtofile;
            DataSource data = new FileDataSource(filename);
            bodypart.setDataHandler(new DataHandler(data));
            bodypart.setFileName("flightsdata.csv");
            multipart.addBodyPart(bodypart);

            message.setContent(multipart);

            Transport.send(message);
            flag = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public boolean emailsendotp(String from, String to, String subject, String text){


        boolean flag = false;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = ""; // your email without @gmail.com
        String password = ""; // your appkey of your email

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            flag = true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return flag;
    }
}