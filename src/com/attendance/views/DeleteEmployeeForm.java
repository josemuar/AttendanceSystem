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
import com.attendance.controller.EmployeeController;
import com.attendance.models.Employee;
import com.attendance.tools.RegexValidationTools;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import attendance_system.TemplateForm;

/*
 * @UpdateEmployeeForm represent the Form which is in charge for allow to modify employee's data.
 */
public class DeleteEmployeeForm extends TemplateForm {

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
	
	private GridBagConstraints gridConstraints;
	public EmployeeController controller;
	private HashMap hashMap;

	/**
	 * Create the frame.
	 */
	public DeleteEmployeeForm() {
		
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout( new GridLayout(1,1) );
		
		content_pane = new JPanel(new BorderLayout() );
		panel_head = new JPanel(new GridLayout(1,1) );
		panel_body = new JPanel(new GridBagLayout());
		panel_footer = new JPanel(new GridLayout(1,2));
		
		
		gridConstraints = new GridBagConstraints();
		
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
		
				
		textfirstname = new JTextField();
		textfirstname.setEditable(false);
		textlastname = new JTextField();
		textlastname.setEditable(false);
		textemail = new JTextField();
		textemail.setEditable(false);
		
		
		combobox = new JComboBox( );
		combobox.addItem( "Casual" );
		combobox.addItem( "Full-Time" );
		combobox.addItem( "Part-Time" );
		combobox.setEnabled(false);
		
		
		JButton btnSave = new JButton("Delete");
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
		
		JLabel lblTitle = new JLabel("Delete Employee");
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
		
	
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		gridConstraints.weightx = 1;
		panel_body.add( lbllastname , gridConstraints );
		
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.weightx = 1;
		panel_body.add( textlastname , gridConstraints );
		
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 2;
		gridConstraints.weightx = 1;
		panel_body.add( lblemail  , gridConstraints);
		
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		gridConstraints.weightx = 1;
		panel_body.add( textemail , gridConstraints );
		
		
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
		
		
		pack();	
	}
	
	
	/**
	 * @saveUser 
	 * updates current employee's information with  the information filled up in the form by the user
	*/
	public void saveUser(){
		
		
		String first_name = textfirstname.getText();
		String last_name = textlastname.getText();
		String email = textemail.getText();
		
		if( first_name.length() > 0 & last_name.length() > 0 & email.length() >0   )
		{
		
			if (first_name.matches(RegexValidationTools.FIRST_NAME_PATTERN.toString())) {
			
				if (last_name.matches(RegexValidationTools.SECOND_NAME_PATTERN.toString())) {
							
					if (email.matches(RegexValidationTools.EMAIL_PATTERN.toString())) {
						
						Employee employee = new Employee();
						employee.set_email(getTextemail().getText());
						employee.set_first_name(getTextfirstname().getText());
						employee.set_last_name(getTextlastname().getText());
						employee.set_type(getCombobox().getSelectedItem().toString());
						
						this.getController().setForm(this);
						this.getController().deleteEmployee(employee);
								
					}
				}	
			}
		}
		else
		{
			this.showWarningMessage( "PLEASE PROVIDE THE INFORMATION REQUIRED IN THE FORM" , "");
		}
	}
	
	/**
	 * @clerAllFields 
	 * wipe out all the fields of the form
	 */
	public void clerAllFields() {
		
		this.getTextfirstname().setText("");
		this.getTextlastname().setText("");
		this.getTextemail().setText("");
		this.getCombobox().setSelectedIndex(0);
		
		lblfirstname_icon.setVisible(false);
		lbllastname_icon.setVisible(false);
		lblemail_icon.setVisible(false);
		
	}
	
	/**
	 * @setHashMap
	 * This function allows to set up the values in the field's Form, Through a hashMap Object.
	 */
	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
		
		//Populating the field's form with the data of the employee who is subject to update.
		this.getTextfirstname().setText( getHashMap().get("first_name").toString() );
		this.getTextlastname().setText( getHashMap().get("last_name").toString() );
		this.getTextemail().setText( getHashMap().get("email").toString() );
		this.getCombobox().setSelectedItem(getHashMap().get("type").toString());

	}

	public JTextField getTextfirstname() {
		return textfirstname;
	}


	public void setTextfirstname(JTextField textfirstname) {
		this.textfirstname = textfirstname;
	}


	public JTextField getTextlastname() {
		return textlastname;
	}


	public void setTextlastname(JTextField textsecondname) {
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
	
	public EmployeeController getController() {
		return controller;
	}

	public void setController(EmployeeController controller) {
		this.controller = controller;
	}


	public HashMap getHashMap() {
		return hashMap;
	}
	
	
}
