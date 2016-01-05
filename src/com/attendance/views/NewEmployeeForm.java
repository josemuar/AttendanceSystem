package com.attendance.views;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.attendace.tools.RegexValidationTools;
import com.attendance.controller.EmployeeController;
import com.attendance.models.Employee;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;



public class NewEmployeeForm extends JFrame {

	private JPanel content_pane;
	private JPanel panel_head;
	private JPanel panel_body;
	private JPanel panel_footer;
	
	private JTextField textfirstname;
	private JTextField textlastname;
	private JTextField textemail;
	private JComboBox combobox;
	
	private JLabel lblfirstname  ;
	private JLabel lbllastname ;
	private JLabel lblemail ;
	private JLabel lblComboBox;
	
	private JLabel lblfirstname_icon  ;
	private JLabel lbllastname_icon ;
	private JLabel lblemail_icon ;
	
	private ImageIcon warning_icon;
	private ImageIcon success_icon;
	
	private String[] EmployeeTypes = {"Casual","Full-Time","Part-Time"};

	/**
	 * Create the frame.
	 */
	public NewEmployeeForm() {
		
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout( new GridLayout(1,1) );
		
		content_pane = new JPanel(new BorderLayout() );
		panel_head = new JPanel(new GridLayout(1,1) );
		panel_body = new JPanel(new GridBagLayout());
		panel_footer = new JPanel(new GridLayout(1,2));
		
		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		
		warning_icon = new ImageIcon(this.getClass().getResource("/warning_icon.png"));
		success_icon = new ImageIcon(this.getClass().getResource("/success_icon.png"));
		
		panel_body.setPreferredSize(new Dimension(450, 200));
		panel_footer.setPreferredSize(new Dimension(450, 50));
		content_pane.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_body.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		lblfirstname  = new JLabel ( "First Name" );
		lbllastname = new JLabel ( "Last Name" );
		lblemail = new JLabel("Email");
		lblComboBox = new JLabel("Employee Type");
		
		lblfirstname_icon  = new JLabel();
		lblfirstname_icon.setPreferredSize(new Dimension(30, 25));
		lblfirstname_icon.setVisible(false);
		
		lbllastname_icon = new JLabel();
		lbllastname_icon.setPreferredSize(new Dimension(30, 25));
		lbllastname_icon.setVisible(false);
		
		lblemail_icon = new JLabel();
		lblemail_icon.setPreferredSize(new Dimension(30, 25));
		lblemail_icon.setVisible(false);
		
		textfirstname = new JTextField();
		textlastname = new JTextField();
		textemail = new JTextField();
		combobox = new JComboBox( EmployeeTypes );
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				saveUser();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblTitle = new JLabel("Register new Employee");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_head.add(lblTitle);
		
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridConstraints.ipady = 20;
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.weightx = 1;
		panel_body.add( lblfirstname, gridConstraints );
		
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		gridConstraints.weightx = 1;
		panel_body.add( textfirstname , gridConstraints);
		
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 0;
		gridConstraints.weightx = 0;
		panel_body.add( lblfirstname_icon  , gridConstraints);
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		gridConstraints.weightx = 1;
		panel_body.add( lbllastname , gridConstraints );
		
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.weightx = 1;
		panel_body.add( textlastname , gridConstraints );
		
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 1;
		gridConstraints.weightx = 0;
		panel_body.add( lbllastname_icon , gridConstraints );
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 2;
		gridConstraints.weightx = 1;
		panel_body.add( lblemail  , gridConstraints);
		
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		gridConstraints.weightx = 1;
		panel_body.add( textemail , gridConstraints );
		
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 2;
		gridConstraints.weightx = 0;
		panel_body.add( lblemail_icon  , gridConstraints);
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 3;
		panel_body.add( lblComboBox, gridConstraints );
		
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 3;
		gridConstraints.gridwidth = 2;
		panel_body.add( combobox, gridConstraints );
		
		panel_footer.add(btnSave);
		panel_footer.add(btnCancel);
		
		content_pane.add(panel_head, BorderLayout.NORTH);
		content_pane.add(panel_body, BorderLayout.CENTER);
		content_pane.add(panel_footer, BorderLayout.SOUTH);
		getContentPane().add(content_pane );
		
		
		textfirstname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				textfirstname.setText(textfirstname.getText().toUpperCase());
				String first_name = textfirstname.getText();
				
				if (first_name.matches(RegexValidationTools.FIRST_NAME_PATTERN.toString()))
				{
					lblfirstname_icon.setIcon(success_icon);
					lblfirstname_icon.setVisible(true);
				}
				else
				{
					lblfirstname_icon.setIcon(warning_icon);
					lblfirstname_icon.setVisible(true);
				}
					
			}
		});
		
		textlastname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				textlastname.setText(textlastname.getText().toUpperCase());
				String last_name = textlastname.getText();
				
				if (last_name.matches(RegexValidationTools.SECOND_NAME_PATTERN.toString()))
				{
					lbllastname_icon.setIcon(success_icon);
					lbllastname_icon.setVisible(true);
				}
				else
				{
					lbllastname_icon.setIcon(warning_icon);
					lbllastname_icon.setVisible(true);
				}
				
			}
		});
		
		
		textemail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String email = textemail.getText();
				
				if (email.matches(RegexValidationTools.EMAIL_PATTERN.toString()))
				{
					lblemail_icon.setIcon(success_icon);
					lblemail_icon.setVisible(true);
				}
				else
				{
					lblemail_icon.setIcon(warning_icon);
					lblemail_icon.setVisible(true);
				}
			}
		});
		
	
		
        // Reorganize the embedded components
		pack();	
	}
	
	
	public void saveUser(){
		
		
		String first_name = textfirstname.getText();
		String second_name = textlastname.getText();
		String email = textemail.getText();
		
		if (first_name.matches(RegexValidationTools.FIRST_NAME_PATTERN.toString())) {
		
			if (second_name.matches(RegexValidationTools.SECOND_NAME_PATTERN.toString())) {
				System.out.println("success se");
		
				
				if (email.matches(RegexValidationTools.EMAIL_PATTERN.toString())) {
					
					Employee newEmployee = new Employee();
					newEmployee.set_email(getTextemail().getText());
					newEmployee.set_first_name(getTextfirstname().getText());
					newEmployee.set_last_name(getTextsecondname().getText());
					newEmployee.set_type(getCombobox().getSelectedItem().toString());
					
					EmployeeController controller = new EmployeeController();
					controller.newEmployee(newEmployee);
							
				}
			}	
		}
	}


	public JTextField getTextfirstname() {
		return textfirstname;
	}


	public void setTextfirstname(JTextField textfirstname) {
		this.textfirstname = textfirstname;
	}


	public JTextField getTextsecondname() {
		return textlastname;
	}


	public void setTextsecondname(JTextField textsecondname) {
		this.textlastname = textsecondname;
	}


	public JTextField getTextemail() {
		return textemail;
	}


	public void setTextemail(JTextField textemail) {
		this.textemail = textemail;
	}


	public JComboBox getCombobox() {
		return combobox;
	}


	public void setCombobox(JComboBox combobox) {
		this.combobox = combobox;
	}
	
	
}
