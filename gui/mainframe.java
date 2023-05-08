package org.example.gui;

import org.example.automation.flight;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.example.automation.easymytrip.getEasymytripflights;
import static org.example.automation.ixigo.getIxigoflghts;
import static org.example.database.main.*;
import static org.example.services.csv.savecsv;
import static org.example.services.emailmain.sendEmail;
import static org.example.services.emailmain.sendresetotp;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Font;
import java.util.Arrays;
import java.util.regex.Pattern;


public class mainframe extends JFrame implements ActionListener {

	private static boolean isloggedin = false;
	private static String mainemail = "";
	private  static String mainotp = "";
	private JTable table;
	private JTable table_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_1;

	JPanel panel;
	JPanel panel_8;
	JPanel panel_1;
	JPanel panel_10;
	JPanel panel_11;
	JPanel panel_6;
	JPanel panel_7;
	DefaultTableModel dtm;
	DefaultTableModel dtm1;
	JPanel forgotpageotpverifymodule;
	ArrayList<flight> easymytripdata;
	ArrayList<flight> ixigodata;
	private JTextField loginuseranamefield;
	private JTextField signuppageusernamefield;
	private JTextField signuppageemailfield;
	private JPasswordField signuppagepasswordfield;
	private JTextField forgotpageemailfield;
	private CardLayout cardLayout;
	private JPasswordField loginpasswordfield;
	private JTextField forgotpageotpverifyfield;
	private JTextField resetpagefirstfield;
	private JTextField resetpagesecondfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe frame = new mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainframe() {
		setSize(new Dimension(800, 600));
		getContentPane().setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardLayout = new CardLayout(0, 0);
		getContentPane().setLayout(cardLayout);
		
		JPanel loginpage = new JPanel();
		getContentPane().add(loginpage, "loginpage");
		loginpage.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		loginpage.add(panel_15, BorderLayout.NORTH);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_15.add(panel_14, BorderLayout.CENTER);
		panel_14.setPreferredSize(new Dimension(600, 400));
        panel_14.setMaximumSize(new Dimension(600, 200)); 
        panel_14.setMinimumSize(panel_14.getPreferredSize());
		FlowLayout fl_panel_14 = new FlowLayout(FlowLayout.CENTER, 350, 5);
		panel_14.setLayout(fl_panel_14);
		
		JPanel panel_20 = new JPanel();
		panel_14.add(panel_20);
		panel_20.setLayout(new BoxLayout(panel_20, BoxLayout.Y_AXIS));
		
		JPanel panel_21 = new JPanel();
		panel_20.add(panel_21);
		panel_21.setLayout(new BoxLayout(panel_21, BoxLayout.Y_AXIS));
		
		JLabel loginusernamelabel = new JLabel("Enter your username: ");
		loginusernamelabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginusernamelabel.setAlignmentX(0.5f);
		panel_21.add(loginusernamelabel);
		
		loginuseranamefield = new JTextField();
		loginuseranamefield.setPreferredSize(new Dimension(7, 30));
		loginuseranamefield.setColumns(25);
		panel_21.add(loginuseranamefield);
		
		JLabel loginpasswordlabel = new JLabel("Enter your password:");
		loginpasswordlabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginpasswordlabel.setAlignmentX(0.5f);
		panel_21.add(loginpasswordlabel);
		
		loginpasswordfield = new JPasswordField();
		loginpasswordfield.setPreferredSize(new Dimension(7, 30));
		loginpasswordfield.setColumns(25);
		panel_21.add(loginpasswordfield);
		
		JPanel panel_19 = new JPanel();
		panel_21.add(panel_19);
		
		JButton logintohomebtn = new JButton("login");
		logintohomebtn.setPreferredSize(new Dimension(70, 25));
		panel_19.add(logintohomebtn);
		logintohomebtn.setName("logintohomebtn");
		logintohomebtn.addActionListener(this);

		JButton logintosignupbtn = new JButton("signup");
		logintosignupbtn.setPreferredSize(new Dimension(80, 25));
		panel_19.add(logintosignupbtn);
		logintosignupbtn.setName("logintosignupbtn");
		logintosignupbtn.addActionListener(this);

		JPanel panel_17 = new JPanel();
		panel_20.add(panel_17);
		
		JButton loginpageforgotbtn = new JButton("forgot password");
		loginpageforgotbtn.setPreferredSize(new Dimension(140, 30));
		panel_17.add(loginpageforgotbtn);
		loginpageforgotbtn.setName("loginpageforgotbtn");
		loginpageforgotbtn.addActionListener(this);
		
		JPanel panel_16 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_16.getLayout();
		flowLayout.setVgap(15);
		panel_15.add(panel_16, BorderLayout.NORTH);
		
		JLabel loginpageheader = new JLabel("Login Page");
		loginpageheader.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_16.add(loginpageheader);
		
		JPanel homepage = new JPanel();
		getContentPane().add(homepage, "homepage");
		homepage.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setSize(new Dimension(800, 600));
		homepage.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton loginpagelogoutbtn = new JButton("Logout");
		loginpagelogoutbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(loginpagelogoutbtn, BorderLayout.EAST);
		loginpagelogoutbtn.setName("loginpagelogoutbtn");
		loginpagelogoutbtn.addActionListener(this);
		
		JPanel panel_36 = new JPanel();
		panel_4.add(panel_36, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Best flight price finder");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_36.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		panel.add(separator);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);

		JLabel lblNewLabel_4 = new JLabel("Enter your staring point :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_5.add(lblNewLabel_4);

		textField_2 = new JTextField();
		panel_5.add(textField_2);
		textField_2.setColumns(15);

		JLabel lblNewLabel_5 = new JLabel("Enter your ending point :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_5.add(lblNewLabel_5);

		textField_3 = new JTextField();
		panel_5.add(textField_3);
		textField_3.setColumns(15);

		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setPreferredSize(new Dimension(80, 25));
		panel_5.add(btnNewButton_1);
		btnNewButton_1.setName("btnNewButton_1");
		btnNewButton_1.addActionListener(this);

		panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		homepage.add(panel_1, BorderLayout.CENTER);

		panel_10 = new JPanel();
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		panel_1.add(panel_10);

		JLabel lblNewLabel10 = new JLabel("Data from easymytrip: ");
		lblNewLabel10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_10.add(lblNewLabel10);
		String[] header = {"flightname","flghtnumber", "stattime", "endtime", "totaljourneytime", "totalstops", "price"};
		Object[][] em = {};
		dtm = new DefaultTableModel(em,header);

		table = new JTable(dtm);
		JScrollPane scrollBar = new JScrollPane(table);

		panel_10.add(scrollBar);
		panel_11 = new JPanel();
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));
		panel_1.add(panel_11);

		JLabel lblNewLabel11 = new JLabel("Data from ixigo: ");
		lblNewLabel11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_11.add(lblNewLabel11);

		String[] header1 = {"flightname","flghtnumber", "stattime", "endtime", "totaljourneytime", "totalstops", "price"};
		Object[][] em1 = {};
		dtm1 = new DefaultTableModel(em1,header1);
		table_1 = new JTable(dtm1);

		JScrollPane scrollBar_1 = new JScrollPane(table_1);
		panel_11.add(scrollBar_1);



		JPanel panel_2 = new JPanel();
		homepage.add(panel_2,BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_8 = new JPanel();
		panel_6.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Enter path to save in csv :");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_8.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(20);
		panel_8.add(textField);
		
		JPanel panel_37 = new JPanel();
		panel_8.add(panel_37);
		
		JButton browsebtn = new JButton("Browse");
		browsebtn.setPreferredSize(new Dimension(120, 25));
		browsebtn.setName("browsebtn");
		browsebtn.setAlignmentX(0.5f);
		panel_37.add(browsebtn);
		browsebtn.addActionListener(this);
		
		JButton btnNewButton = new JButton("save");
		btnNewButton.setPreferredSize(new Dimension(80, 25));
		btnNewButton.setName("btnNewButton");
		btnNewButton.addActionListener(this);
		btnNewButton.setAlignmentX(0.5f);
		panel_37.add(btnNewButton);

		panel_7 = new JPanel();
		panel_2.add(panel_7);

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_2 = new JLabel("Enter the email to csv to:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel_9.add(textField_1);
		
		JPanel panel_35 = new JPanel();
		panel_9.add(panel_35);
		
		JButton mailtouseremail = new JButton("send mail to above email");
		mailtouseremail.setPreferredSize(new Dimension(190, 25));
		panel_35.add(mailtouseremail);
		mailtouseremail.setName("mailtouseremail");
		mailtouseremail.addActionListener(this);
		
		JButton btnNewButton_2 = new JButton("send mail myself");
		btnNewButton_2.setPreferredSize(new Dimension(150, 25));
		btnNewButton_2.setName("btnNewButton_2");
		panel_35.add(btnNewButton_2);
		btnNewButton_2.setName("btnNewButton_2");
		btnNewButton_2.addActionListener(this);
		
		JPanel signuppage = new JPanel();
		getContentPane().add(signuppage, "signuppage");
		signuppage.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		signuppage.add(panel_12, BorderLayout.NORTH);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_13.getLayout();
		flowLayout_1.setHgap(350);
		panel_12.add(panel_13, BorderLayout.CENTER);
		
		JPanel panel_23 = new JPanel();
		panel_13.add(panel_23);
		panel_23.setLayout(new BoxLayout(panel_23, BoxLayout.Y_AXIS));
		
		JPanel panel_18 = new JPanel();
		panel_23.add(panel_18);
		panel_18.setLayout(new BoxLayout(panel_18, BoxLayout.Y_AXIS));
		
		JLabel signuppageusernamelabel = new JLabel("Enter your username:");
		signuppageusernamelabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		signuppageusernamelabel.setPreferredSize(new Dimension(100, 20));
		signuppageusernamelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_18.add(signuppageusernamelabel);
		
		signuppageusernamefield = new JTextField();
		signuppageusernamefield.setPreferredSize(new Dimension(80, 25));
		signuppageusernamefield.setColumns(10);
		panel_18.add(signuppageusernamefield);
		
		JLabel signuppageemaillabel = new JLabel("Enter your email:");
		signuppageemaillabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		signuppageemaillabel.setPreferredSize(new Dimension(80, 20));
		signuppageemaillabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_18.add(signuppageemaillabel);
		
		signuppageemailfield = new JTextField();
		signuppageemailfield.setPreferredSize(new Dimension(7, 25));
		signuppageemailfield.setColumns(10);
		panel_18.add(signuppageemailfield);
		
		JLabel signuppagepasswordlabel = new JLabel("Enter your Password:");
		signuppagepasswordlabel.setPreferredSize(new Dimension(100, 20));
		signuppagepasswordlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		signuppagepasswordlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_18.add(signuppagepasswordlabel);
		
		signuppagepasswordfield = new JPasswordField();
		signuppagepasswordfield.setPreferredSize(new Dimension(7, 25));
		panel_18.add(signuppagepasswordfield);
		
		JPanel panel_34 = new JPanel();
		panel_18.add(panel_34);
		
		JButton signuppagesignupbtn = new JButton("Sign up");
		signuppagesignupbtn.setPreferredSize(new Dimension(90, 25));
		signuppagesignupbtn.setName("signuppagesignupbtn");
		signuppagesignupbtn.setAlignmentX(0.5f);
		panel_34.add(signuppagesignupbtn);
		signuppagesignupbtn.addActionListener(this);
		
		JButton signuppagebacktologinpagebtn = new JButton("Back to Login");
		signuppagebacktologinpagebtn.setPreferredSize(new Dimension(120, 25));
		panel_34.add(signuppagebacktologinpagebtn);
		signuppagebacktologinpagebtn.setName("signuppagebacktologinpagebtn");
		signuppagebacktologinpagebtn.addActionListener(this);
		
		JPanel panel_25 = new JPanel();
		panel_12.add(panel_25, BorderLayout.NORTH);
		
		JLabel signuppageheader = new JLabel("Sign up Page");
		signuppageheader.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_25.add(signuppageheader);
		
		JPanel passwordresetpage = new JPanel();
		getContentPane().add(passwordresetpage, "passwordresetpage");
		passwordresetpage.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_22 = new JPanel();
		passwordresetpage.add(panel_22, BorderLayout.NORTH);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_24 = new JPanel();
		panel_22.add(panel_24, BorderLayout.NORTH);
		
		JLabel forgotpageheader = new JLabel("Password Reset Page");
		forgotpageheader.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_24.add(forgotpageheader);
		
		JPanel forgotpageemailmodule = new JPanel();
		FlowLayout fl_forgotpageemailmodule = (FlowLayout) forgotpageemailmodule.getLayout();
		fl_forgotpageemailmodule.setHgap(350);
		panel_22.add(forgotpageemailmodule, BorderLayout.CENTER);
		
		JPanel panel_27 = new JPanel();
		panel_27.setPreferredSize(new Dimension(300, 100));
		forgotpageemailmodule.add(panel_27);
		panel_27.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_28 = new JPanel();
		panel_28.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_27.add(panel_28);
		panel_28.setLayout(new BoxLayout(panel_28, BoxLayout.Y_AXIS));
		
		JLabel forgotpageemaillabel = new JLabel("Enter your email:");
		forgotpageemaillabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		forgotpageemaillabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_28.add(forgotpageemaillabel);
		
		forgotpageemailfield = new JTextField();
		forgotpageemailfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		forgotpageemailfield.setPreferredSize(new Dimension(15, 25));
		forgotpageemailfield.setMinimumSize(new Dimension(15, 20));
		panel_28.add(forgotpageemailfield);
		forgotpageemailfield.setColumns(20);
		
		JPanel panel_38 = new JPanel();
		panel_28.add(panel_38);
		
		JButton forgotpagesendotpbtn = new JButton("send OTP");
		forgotpagesendotpbtn.setPreferredSize(new Dimension(100, 25));
		forgotpagesendotpbtn.setName("forgotpagesendotpbtn");
		forgotpagesendotpbtn.addActionListener(this);
		forgotpagesendotpbtn.setAlignmentX(0.5f);
		panel_38.add(forgotpagesendotpbtn);
		
		JButton passwordpagebacktologinbtn = new JButton("Back to login");
		passwordpagebacktologinbtn.setPreferredSize(new Dimension(120, 25));
		panel_38.add(passwordpagebacktologinbtn);
		passwordpagebacktologinbtn.setName("passwordpagebacktologinbtn");
		passwordpagebacktologinbtn.addActionListener(this);

		forgotpageotpverifymodule = new JPanel();
		panel_22.add(forgotpageotpverifymodule, BorderLayout.SOUTH);
		
		JPanel panel_30 = new JPanel();
		forgotpageotpverifymodule.add(panel_30);
		panel_30.setLayout(new BoxLayout(panel_30, BoxLayout.Y_AXIS));
		
		JLabel forgotpageotpverifylabel = new JLabel("Enter Your OTP:");
		forgotpageotpverifylabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		forgotpageotpverifylabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_30.add(forgotpageotpverifylabel);
		
		forgotpageotpverifyfield = new JTextField();
		forgotpageotpverifyfield.setPreferredSize(new Dimension(7, 25));
		forgotpageotpverifyfield.setColumns(20);
		panel_30.add(forgotpageotpverifyfield);
		
		JButton forgotpageotpverifybtn = new JButton("Verify OTP");
		forgotpageotpverifybtn.setPreferredSize(new Dimension(90, 25));
		forgotpageotpverifybtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_30.add(forgotpageotpverifybtn);
		forgotpageotpverifybtn.setName("forgotpageotpverifybtn");
		forgotpageotpverifybtn.addActionListener(this);
		
		JPanel resetpage = new JPanel();
		getContentPane().add(resetpage, "resetpage");
		resetpage.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_26 = new JPanel();
		resetpage.add(panel_26, BorderLayout.NORTH);
		panel_26.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_29 = new JPanel();
		panel_26.add(panel_29, BorderLayout.NORTH);
		
		JLabel resetpageheader = new JLabel("Reset Password");
		resetpageheader.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_29.add(resetpageheader);
		
		JPanel panel_31 = new JPanel();
		panel_26.add(panel_31, BorderLayout.CENTER);
		
		JPanel panel_32 = new JPanel();
		panel_31.add(panel_32);
		panel_32.setLayout(new BoxLayout(panel_32, BoxLayout.Y_AXIS));
		
		JLabel resetpagefirstlabel = new JLabel("Enter your new password :");
		resetpagefirstlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resetpagefirstlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_32.add(resetpagefirstlabel);
		
		resetpagefirstfield = new JTextField();
		resetpagefirstfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_32.add(resetpagefirstfield);
		resetpagefirstfield.setColumns(20);
		
		JLabel resetpagesecondlabel = new JLabel("Re type your password: ");
		resetpagesecondlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resetpagesecondlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_32.add(resetpagesecondlabel);
		
		resetpagesecondfield = new JTextField();
		resetpagesecondfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_32.add(resetpagesecondfield);
		resetpagesecondfield.setColumns(20);
		
		JPanel panel_33 = new JPanel();
		panel_32.add(panel_33);
		
		JButton resetpagesubmitbtn = new JButton("Submit");
		resetpagesubmitbtn.setPreferredSize(new Dimension(100, 25));
		resetpagesubmitbtn.setAlignmentX(0.5f);
		panel_33.add(resetpagesubmitbtn);
		resetpagesubmitbtn.setName("resetpagesubmitbtn");
		resetpagesubmitbtn.addActionListener(this);
		
		JButton resetpagebbacktologinpage = new JButton("back to login page");
		resetpagebbacktologinpage.setPreferredSize(new Dimension(150, 25));
		panel_33.add(resetpagebbacktologinpage);
		resetpagebbacktologinpage.setName("resetpagebbacktologinpage");
		resetpagebbacktologinpage.addActionListener(this);


		panel_1.setVisible(false);
		panel_6.setVisible(false);
		panel_7.setVisible(false);
		forgotpageotpverifymodule.setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		System.out.println(btn.getName());
		if(btn.getName().equals("logintohomebtn")){
			handleloginbtn();
		}
		else if(btn.getName().equals("logintosignupbtn")){
			System.out.println("logintosignup");
			showcard("signuppage");
		}
		else if(btn.getName().equals("loginpageforgotbtn")){
			System.out.println("passwordresetpage");
			showcard("passwordresetpage");
		}
		//signup page
		else if(btn.getName().equals("signuppagesignupbtn")){
			System.out.println("adduser");
			adduser();
		}
		else if(btn.getName().equals("signuppagebacktologinpagebtn")){
			System.out.println("signuppagebacktologinpagebtn");
			showcard("loginpage");
		}
		//forgotpage
		else if(btn.getName()=="forgotpagesendotpbtn"){
			String otp = sendotp();
			if(otp.length()==6) {
				mainotp = otp;
				JOptionPane.showMessageDialog(panel,"OTP sent successfully");
				forgotpageotpverifymodule.setVisible(true);
			}
		}
		else if(btn.getName().equals("forgotpageotpverifybtn")) {
			String userenteredotp = forgotpageotpverifyfield.getText();
			if(userenteredotp.equals(mainotp)){
				JOptionPane.showMessageDialog(panel, "verification successful");
				mainemail = forgotpageemailfield.getText();
				showcard("resetpage");
			}
			else{
				JOptionPane.showMessageDialog(panel, "Please enter correct OTP");
			}
		}
		else if(btn.getName().equals("passwordpagebacktologinbtn")){
			showcard("loginpage");
		}
		//resetpage
		else if(btn.getName().equals("resetpagesubmitbtn")){
			if(updatepassword()){
				JOptionPane.showMessageDialog(panel, "password reset successful, redirecting back to login page");
				mainemail = "";
				showcard("loginpage");
			}
			else{
				System.out.println("password not updated");
			}

		}
		else if(btn.getName().equals("resetpagebbacktologinpage")){
			mainemail = "";
			showcard("loginpage");
		}
		//homepage
		else if(btn.getName()=="btnNewButton_1"){
			String from = textField_2.getText();
			String to = textField_3.getText();
			try {
				getdatecreatetable(from, to);
			} catch (InterruptedException ex) {
				System.out.println("problem in getting data");
			}
		} else if(btn.getName()=="browsebtn"){
			browse();
		}
		else if(btn.getName().equals("btnNewButton")){
			ArrayList<ArrayList<flight>> lists = new ArrayList<>();
			lists.add(easymytripdata);
			lists.add(ixigodata);
			saveascsv(lists);
		}else if(btn.getName().equals("mailtouseremail")){
			ArrayList<ArrayList<flight>> lists = new ArrayList<>();
			lists.add(easymytripdata);
			lists.add(ixigodata);
			String email = textField_1.getText();
			sendemail(lists,email);
		}
		else if(btn.getName().equals("btnNewButton_2")){
			ArrayList<ArrayList<flight>> lists = new ArrayList<>();
			lists.add(easymytripdata);
			lists.add(ixigodata);
			sendemail(lists, mainemail);
		}
		else if(btn.getName().equals("loginpagelogoutbtn")){
			JOptionPane.showMessageDialog(panel, "logout successful");
			mainemail = "";
			panel_1.setVisible(false);
			panel_6.setVisible(false);
			panel_7.setVisible(false);
			loginuseranamefield.setText("");
			loginpasswordfield.setText("");
			textField_2.setText("");
			textField_3.setText("");
			showcard("loginpage");
		}

	}

	private boolean updatepassword() {
		String firstpass = resetpagefirstfield.getText();
		String secondpass = resetpagesecondfield.getText();

		if(firstpass.length()<8 && secondpass.length()<8){
			JOptionPane.showMessageDialog(panel,"password should be atleast 8 characters");
		}
		else if(firstpass.length()<8){
			JOptionPane.showMessageDialog(panel, "password is short, it should be atleast 8 characters long");
		}
		else if(secondpass.length()<8){
			JOptionPane.showMessageDialog(panel, "Re typed password is short plesase reenter password again");
		}
		else if(!secondpass.equals(firstpass)){
			JOptionPane.showMessageDialog(panel,"Password doesn't match with password");
		}
		else if(firstpass.equals(secondpass)){
			if(resetpassword(mainemail,firstpass)){
				return true;
			}
		}
		return false;
	}

	private String sendotp() {
		String email = forgotpageemailfield.getText();

		if(!isValidemail(email)) {
			JOptionPane.showMessageDialog(panel, "Please enter vaild email");
		}
		else if(!isemailpresent(email)){
			JOptionPane.showMessageDialog(panel, "Email not found, please create a new account");
		}
		else {
			String otp = sendresetotp(email);
			mainemail = email;
			return otp;
		}
		return "";
	}

	private void handleloginbtn() {
		String username = loginuseranamefield.getText();
		char[] passwordarr = loginpasswordfield.getPassword();
		String password = "";
		for(char c:passwordarr){
			password += c;
		}
		if(username.length()==0 && password.length()==0){
			JOptionPane.showMessageDialog(panel, "Please enter username and password");
		}
		else if(username.length()==0){
			JOptionPane.showMessageDialog(panel,"Please enter username");
		}
		else if(password.length()==0){
			JOptionPane.showMessageDialog(panel, "Please enter password");
		}
		else {
			String email = auth(username, password);
//			System.out.println(email);
			if (email.length()>0) {
				isloggedin = true;
				mainemail = email;
				showcard("homepage");
			} else {
				JOptionPane.showMessageDialog(panel, "Either username or password is worng");
			}
		}
	}

	public static boolean isValidemail(String email)
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
				"[a-zA-Z0-9_+&*-]+)*@" +
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
				"A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}
	private void adduser() {
		String username = signuppageusernamefield.getText();
		String email = signuppageemailfield.getText();
		char[] passwordarr = signuppagepasswordfield.getPassword();
		String password = "";
		for (char c : passwordarr) {
			password += c;
		}
		if(username.length()==0 && email.length()==0 && password.length()==0){
			JOptionPane.showMessageDialog(panel,"all fields should be filled");
		}
		else if(username.length() ==0){
			JOptionPane.showMessageDialog(panel,"Please enter username");
		}
		else if(!isValidemail(email)){
			JOptionPane.showMessageDialog(panel, "please enter correct email");
			return ;
		}
		else if (passwordarr.length < 8) {
			JOptionPane.showMessageDialog(panel, "password should be atleast 8 characters");
		} else {
			insertdata(username, password, email);
			JOptionPane.showMessageDialog(panel, "Signup successful");
			showcard("loginpage");
		}
	}

	private void showcard(String card) {
		System.out.print("cardchange");
		cardLayout.show(getContentPane(), card);
	}

	private void getdatecreatetable(String from, String to) throws InterruptedException {

		if(from.length()>0 && to.length()>0) {
			easymytripdata = getEasymytripflights(from, to);

			for(int i =0;i<easymytripdata.size();i++){
				flight cur = easymytripdata.get(i);
				dtm.addRow(new Object[]{cur.flightname, cur.flightnumber, cur.starttime, cur.endtime, cur.totaljourneytime, cur.totalstops, cur.price});
			}
			ixigodata = getIxigoflghts(from, to);

			for(int i =0;i<ixigodata.size();i++){
				flight cur = ixigodata.get(i);
				dtm1.addRow(new Object[]{cur.flightname, cur.flightnumber, cur.starttime, cur.endtime, cur.totaljourneytime, cur.totalstops, cur.price});
			}
			panel_1.setVisible(true);
			panel_6.setVisible(true);
			panel_7.setVisible(true);
		}
		else{
			JOptionPane.showMessageDialog(panel, "Please enter both start and end of your journey");
		}

	}

	private void browse() {
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(filechooser.showOpenDialog(panel_8)==JFileChooser.APPROVE_OPTION){
			File file = filechooser.getSelectedFile();
			String path = file.getAbsolutePath() + "\\flightsdata.csv";
			textField.setText(path);
		}
	}

	private void sendemail(ArrayList<ArrayList<flight>> lists, String email) {
		

		System.out.println(email);
		if(email.length()>0){
			//creating csv file in current path
			Path currentrelativepath = Paths.get("");
			String path = currentrelativepath.toAbsolutePath().toString();

			//creating csv file in the current path
			path += "\\flightsdata.csv";
			savecsv(path, lists);
			if(sendEmail(email, path)){
				File csvfile = new File(path);
				csvfile.delete();
				JOptionPane.showMessageDialog(panel, "Email sent");
			}
			else{
				JOptionPane.showMessageDialog(panel, "Please enter valid mailid");
			}
		}
		else{
			JOptionPane.showMessageDialog(panel, "Please enter email");
		}
	}



	private void saveascsv(ArrayList<ArrayList<flight>> lists) {
		String path = textField.getText();
		if(path.length()>0) {
			if (savecsv(path, lists)) {
				JOptionPane.showMessageDialog(panel, "csv file saved.");
			} else {
				JOptionPane.showMessageDialog(panel, "please choose correct path");
			}
		}
		else{
			JOptionPane.showMessageDialog(panel, "please enter path or choose");
		}
	}
}
