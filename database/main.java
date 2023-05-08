package org.example.database;

import java.sql.*;

public class main {

    public static boolean insertdata(String username, String password, String email){
        System.out.println("inside insertdata function");
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/{your database name}",
                    "postgres", "postgres");
            System.out.println("Opened database successfully");

            Statement st = c.createStatement();
            PreparedStatement ps = c.prepareStatement("INSERT INTO users VALUES (?,?,?);");
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
        return true;
    }
    public static String auth(String username, String password){
        System.out.println("inside auth function");
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/{your database name}",
                    "postgres", "postgres");
            System.out.println("Opened database successfully");

            PreparedStatement ps = c.prepareStatement("select * from users;");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                String name = rs.getString("username");
                String pass = rs.getString("password");
                String email = rs.getString("email");
                while(name.charAt(name.length()-1)==' '){
                    name = name.substring(0,name.length()-1);
                }
                while(pass.charAt(pass.length()-1)==' '){
                    pass = pass.substring(0,pass.length()-1);
                }
                while(email.charAt(email.length()-1)==' '){
                    email = email.substring(0,email.length()-1);
                }
//                System.out.println(name+" " + pass + " " + username + " "  + password);
                if(name.equals(username) && pass.equals(password)){
                    return email;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return "";
        }
        return "";
    }
    public static boolean isemailpresent(String email){
        System.out.println("inside auth function");
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/{your database name}",
                    "postgres", "postgres");
            System.out.println("Opened database successfully");

            PreparedStatement ps = c.prepareStatement("select * from users;");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                String curemail = rs.getString("email");
                while(curemail.charAt(curemail.length()-1)==' '){
                    curemail = curemail.substring(0,curemail.length()-2);
                }
                if(curemail.equals(email)){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
        return false;
    }
    public static boolean resetpassword(String email, String password){
        System.out.println("inside auth function");
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/{your database name}",
                    "postgres", "postgres");
            System.out.println("Opened database successfully");

            PreparedStatement ps = c.prepareStatement("select * from users;");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                String curemail = rs.getString("email");
                while(curemail.charAt(curemail.length()-1)==' '){
                    curemail = curemail.substring(0,curemail.length()-2);
                }
                if(curemail.equals(email)){
                    Statement st = c.createStatement();
                    PreparedStatement ps1 = c.prepareStatement("UPDATE users SET password=(?) WHERE email=(?);");
                    ps1.setString(1,password);
                    ps1.setString(2,email);
                    ps1.executeUpdate();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
        return false;
    }
}
