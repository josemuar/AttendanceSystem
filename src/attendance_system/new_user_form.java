package attendance_system;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Font;
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

public class new_user_form extends JFrame {

	private JTextField textfirstname;
	private JTextField textsecondname;
	private JTextField textemail;
	

	/**
	 * Create the frame.
	 */
	public new_user_form() {
		
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout( new GridLayout(1,1) );
		
		JPanel content_pane = new JPanel(new BorderLayout() );
		JPanel panel_head = new JPanel(new GridLayout(1,1) );
		JPanel panel_body = new JPanel(new GridLayout(3,2));
		GridLayout gl_panel_footer = new GridLayout(1,2);
		gl_panel_footer.setHgap(2);
		JPanel panel_footer = new JPanel(gl_panel_footer);
		
		
		panel_body.setPreferredSize(new Dimension(450, 100));
		panel_footer.setPreferredSize(new Dimension(450, 50));
		content_pane.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_body.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel lblfirstname  = new JLabel ( "First Name" );
		JLabel lblsecondname = new JLabel ( "Second Name" );
		JLabel lblemail = new JLabel("Email");
		
		textfirstname = new JTextField();
		textsecondname = new JTextField();
		textemail = new JTextField();
		
		
		String first_name;
		
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
		
		JLabel lblTitle = new JLabel("New User");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_head.add(lblTitle);
		
		
		panel_body.add( lblfirstname );
		panel_body.add( textfirstname );
		
		panel_body.add( lblsecondname );
		panel_body.add( textsecondname );
		
		panel_body.add( lblemail );
		panel_body.add( textemail );
		
		panel_footer.add(btnSave);
		panel_footer.add(btnCancel);
		
		//getContentPane().add(panel_head, BorderLayout.NORTH);
		//getContentPane().add(panel_body, BorderLayout.CENTER);
		//getContentPane().add(panel_footer, BorderLayout.SOUTH);
		
		content_pane.add(panel_head, BorderLayout.NORTH);
		content_pane.add(panel_body, BorderLayout.CENTER);
		content_pane.add(panel_footer, BorderLayout.SOUTH);
		getContentPane().add(content_pane );
		 
        // Reorganize the embedded components
		pack();	
	}
	
	
	public void saveUser(){
		Pattern pattern = Pattern.compile("([A-Z]{1,20})((\\s{1}[A-Z]{1,20}){0,1})");
		String first_name = textfirstname.getText();
		
		
		if (first_name.matches(pattern.toString())) {
			
			System.out.println("success");
			
		}else
		{
			System.out.println("unsuccess");
		}
		
		
	}
	
	
}
