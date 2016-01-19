package attendance_system;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.attendance.views.NewEmployeeForm;
import com.attendance.controller.EmployeeController;
import com.attendance.services.RESTServiceDirectory;
import com.attendance.tools.ButtonEditor;
import com.attendance.tools.ButtonRenderer;
import com.attendance.tools.EmployeeTable;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class MainUI {

	private JFrame frame;
	private EmployeeTable table;
	private JPanel head_panel;
	private JScrollPane tablePane;
	
	public EmployeeController controller;
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	* Create the application.
	*/
	public MainUI() {
		//instantiate the Employee Controller (MVC approach)
		controller = new EmployeeController();
		//Passing a MainUI reference to the controller
		controller.setMainUI(this);
		
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		table = new EmployeeTable();
		table.setController(this.getController());
		table.populate();
		
		
		ButtonRenderer delete_btn =  new ButtonRenderer();
		delete_btn.setImage_icon( new ImageIcon(this.getClass().getResource("/trash_icon.png")) );
		table.getColumn("Delete").setCellRenderer( delete_btn );
		table.getColumn("Delete").setPreferredWidth(20);
		ButtonEditor editor_delete_btn = new ButtonEditor(new JCheckBox());
		editor_delete_btn.setController(this.getController());
		table.getColumn("Delete").setCellEditor(editor_delete_btn);
		
		
		ButtonRenderer update_btn =  new ButtonRenderer();
		update_btn.setImage_icon( new ImageIcon(this.getClass().getResource("/update_icon.png")) );
		table.getColumn("Edit").setCellRenderer( update_btn );
		table.getColumn("Edit").setPreferredWidth(20);
		ButtonEditor editor_edit_btn = new ButtonEditor(new JCheckBox());
		editor_edit_btn.setController(this.getController());
		table.getColumn("Edit").setCellEditor(editor_edit_btn);
		
		
		table.setRowHeight(40);
		
		table.repaint();
		
		tablePane = new JScrollPane(table);
		tablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		
		head_panel = new JPanel();
		frame.getContentPane().add(tablePane, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel("New label");
		frame.getContentPane().add(head_panel, BorderLayout.NORTH);
		head_panel.setLayout(new GridLayout(1, 1));
		head_panel.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);
		
		JMenuItem mntmNewUser = new JMenuItem("New user");
		mntmNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pop up Form to create a new employee
				controller.newEmployeeForm();
			}
		});
		
		mnUser.add(mntmNewUser);
		
		JMenu mnTag = new JMenu("Tag");
		menuBar.add(mnTag);
		
		JMenuItem mntmNewTag = new JMenuItem("New TAG");
		mnTag.add(mntmNewTag);
		
		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);
		
		JMenuItem mntmListUsers = new JMenuItem("List users");
		mnReport.add(mntmListUsers);
		
		JMenuItem mntmListTags = new JMenuItem("list TAGs");
		mnReport.add(mntmListTags);	

	}
	
	/**
	 * @updateEnployeeTableView Refresh the JTable who contains all the employee records. (implies new call to Dynamo database at the cloud)
	 */
	public void updateEmployeeTableComponent()
	{
		
		this.getTable().populate();
		
		ButtonRenderer delete_btn =  new ButtonRenderer();
		delete_btn.setImage_icon( new ImageIcon(this.getClass().getResource("/trash_icon.png")) );
		table.getColumn("Delete").setCellRenderer( delete_btn );
		table.getColumn("Delete").setPreferredWidth(20);
		ButtonEditor editor_delete_btn = new ButtonEditor(new JCheckBox());
		editor_delete_btn.setController(this.getController());
		table.getColumn("Delete").setCellEditor(editor_delete_btn);
		
		
		ButtonRenderer update_btn =  new ButtonRenderer();
		update_btn.setImage_icon( new ImageIcon(this.getClass().getResource("/update_icon.png")) );
		table.getColumn("Edit").setCellRenderer( update_btn );
		table.getColumn("Edit").setPreferredWidth(20);
		ButtonEditor editor_edit_btn = new ButtonEditor(new JCheckBox());
		editor_edit_btn.setController(this.getController());
		table.getColumn("Edit").setCellEditor(editor_edit_btn);
		
	}

	public EmployeeController getController() {
		return controller;
	}

	public void setController(EmployeeController controller) {
		this.controller = controller;
	}

	public EmployeeTable getTable() {
		return table;
	}

	public void setTable(EmployeeTable table) {
		this.table = table;
	}
	
	
}
