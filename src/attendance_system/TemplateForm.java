package attendance_system;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Jose Luis Munoz Aricapa
 * This class is used (extended) by JFrame objects which need to show Dialog messages to the user
 *
 */
public class TemplateForm extends JFrame {
	
	
	/**
	 * @showInformationMessage just shows an Information Message
	 * @param message: just the message to show 
	 * @param title: the title of the dialog Frame. if null it will show default title
	 */
	public void showInformationMessage(String message, String title)
	{
		if ( title.isEmpty() )
			JOptionPane.showMessageDialog (null, message, "Information Message" , JOptionPane.INFORMATION_MESSAGE);	
		else
			JOptionPane.showMessageDialog (null, message, title, JOptionPane.INFORMATION_MESSAGE);	
	}
	
	/**
	 * @showInformationMessage just shows an Warning Message
	 * @param message: just the message to show 
	 * @param title: the title of the dialog Frame. if null it will show default title
	 */
	public void showWarningMessage(String message, String title)
	{
		if ( title.isEmpty() )
			JOptionPane.showMessageDialog (null, message, "Warning Message", JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog (null, message, title , JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * @showInformationMessage just shows an Error Message
	 * @param message: just the message to show 
	 * @param title: the title of the dialog Frame. if null it will show default title
	 */
	public void showErrorMessage(String message, String title)
	{
		if ( title.isEmpty() )
			JOptionPane.showMessageDialog (null, message, "Error Message", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog (null, message, title, JOptionPane.ERROR_MESSAGE);
			
	}
	
	public void clerAllFields() 
	{
		
	}
	
	
	
	

}
