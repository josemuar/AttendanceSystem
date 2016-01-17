package com.attendance.tools;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;


/**
* @version 1.0 11/09/98
*/
public class ButtonRenderer extends JButton implements TableCellRenderer {

	private ImageIcon image_icon;
	
	public ButtonRenderer() {
	  //setOpaque(true);
	}
	
	
	
	 
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		  
		if (isSelected) {
		    setForeground(table.getSelectionForeground());
		    setBackground(table.getSelectionBackground());
		} else{
		    setForeground(table.getForeground());
		    setBackground(UIManager.getColor("Button.background"));
		}
		  //setText( (value ==null) ? "" : value.toString() );
		return this;
	}


	public ImageIcon getImage_icon() {
		return image_icon;
	}


	public void setImage_icon(ImageIcon image_icon) {
		this.image_icon = image_icon;
		setIcon( getImage_icon() );
	}


}

