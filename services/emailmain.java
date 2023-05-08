package org.example.services;


import java.util.Random;

public class emailmain {



    public static boolean sendEmail(String mailid, String pathtofile){
        Email email = new Email();
        String from = ""; // email from which you want to send the email from
        String subject = "this is best flight price finder";
        String text = "This is the csv with the data you requested for ";
        boolean status = email.send(from, mailid, subject, text,pathtofile);

        if(status){
            return true;
        }
        return false;
    }
    public static String sendresetotp(String mailid){
        String otp = "";
        Random r = new Random();
        for(int i =0;i<6;i++){
            otp += "" + r.nextInt(9);
        }
        Email email = new Email();
        String from = ""; // email from which you want to send the email from
        String subject = "this is from best flight price finder";
        String text = "This is the otp for password reset: " + otp;

        boolean status = email.emailsendotp(from, mailid, subject,text);
        if(status){
            return otp;
        }
        return "";
    }
}
