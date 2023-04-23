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
import static org.example.services.csv.savecsv;
import static org.example.services.emailmain.sendEmail;



public class mainframe extends JFrame implements ActionListener {

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
	ArrayList<flight> easymytripdata;
	ArrayList<flight> ixigodata;
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
		getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setSize(new Dimension(800, 600));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("Best flight price finder");
		panel_4.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		panel.add(separator);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);

		JLabel lblNewLabel_4 = new JLabel("Enter your staring point :");
		panel_5.add(lblNewLabel_4);

		textField_2 = new JTextField();
		panel_5.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Enter your ending point :");
		panel_5.add(lblNewLabel_5);

		textField_3 = new JTextField();
		panel_5.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton_1 = new JButton("Search");
		panel_5.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);

		panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		getContentPane().add(panel_1, BorderLayout.CENTER);

		panel_10 = new JPanel();
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		panel_1.add(panel_10);

		JLabel lblNewLabel10 = new JLabel("Data from easymytrip: ");
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
		panel_11.add(lblNewLabel11);

		String[] header1 = {"flightname","flghtnumber", "stattime", "endtime", "totaljourneytime", "totalstops", "price"};
		Object[][] em1 = {};
		dtm1 = new DefaultTableModel(em1,header1);
		table_1 = new JTable(dtm1);

		JScrollPane scrollBar_1 = new JScrollPane(table_1);
		panel_11.add(scrollBar_1);



		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_8 = new JPanel();
		panel_6.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Enter path to save in csv :");
		panel_8.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setColumns(10);
		panel_8.add(textField);

		JButton browsebtn = new JButton("Browse");
		panel_8.add(browsebtn);
		browsebtn.addActionListener(this);

		JButton btnNewButton = new JButton("save");
		panel_8.add(btnNewButton);
		btnNewButton.addActionListener(this);

		panel_7 = new JPanel();
		panel_2.add(panel_7);

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_2 = new JLabel("Enter the email to csv to:");
		panel_9.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_9.add(textField_1);

		JButton btnNewButton_2 = new JButton("send mail");
		panel_9.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);



		panel_1.setVisible(false);
		panel_6.setVisible(false);
		panel_7.setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Search"){
			String from = textField_2.getText();
			String to = textField_3.getText();
			try {
				getdatecreatetable(from, to);
			} catch (InterruptedException ex) {
				System.out.println("problem in getting data");
			}
		} else if(e.getActionCommand()=="Browse"){
			browse();
		}
		else if(e.getActionCommand()=="save"){
			ArrayList<ArrayList<flight>> lists = new ArrayList<>();
			lists.add(easymytripdata);
			lists.add(ixigodata);
			saveascsv(lists);
		}else if(e.getActionCommand()=="send mail"){
			ArrayList<ArrayList<flight>> lists = new ArrayList<>();
			lists.add(easymytripdata);
			lists.add(ixigodata);
			sendemail(lists);
		}

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

	private void sendemail(ArrayList<ArrayList<flight>> lists) {
		String email = textField_1.getText();

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
