package attendance_system;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.attendance.views.NewEmployeeForm;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUI {

	private JFrame frame;

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);
		
		JMenuItem mntmNewUser = new JMenuItem("New user");
		mntmNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEmployeeForm  form = new NewEmployeeForm();
				form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				form.setVisible(true);
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
	
}
