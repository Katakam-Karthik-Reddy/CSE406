package org.example.services;


public class emailmain {



    public static boolean sendEmail(String mailid, String pathtofile){
        Email email = new Email();
        String from = "songoku19841203@gmail.com";
        String subject = "this is best flight finder";
        String text = "This is the csv with the data you requested for ";
        boolean status = email.send(from, mailid, subject, text,pathtofile);

        if(status){
            return true;
        }
        return false;
    }
}
