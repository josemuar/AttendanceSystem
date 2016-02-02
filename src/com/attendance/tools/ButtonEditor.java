package com.attendance.tools;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.json.JSONObject;

import com.attendance.controller.EmployeeController;

public class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;
	  private String    label;
	  private boolean   isPushed;
	  public EmployeeController controller;
	  
	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  System.out.println(label + ": Ouch!");
	        fireEditingStopped();
	      }
	    });
	  }
	  
	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    /*
		  if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else{
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }*/
	    /*
	    String first_name = table.getModel().getValueAt(row, 0).toString();
	    String last_name =table.getModel().getValueAt(row, 1).toString() ;
	    String email = table.getModel().getValueAt(row, 2).toString() ;
	    String type = table.getModel().getValueAt(row, 3).toString() ;
	    					
		String[] record = { first_name, last_name, email, "", type  };
		*/
		HashMap <String, String> hash_map = new HashMap<String,String>();
		hash_map.put("first_name", table.getModel().getValueAt(row, 0).toString()); 
		hash_map.put("last_name", table.getModel().getValueAt(row, 1).toString()); 
		hash_map.put("email", table.getModel().getValueAt(row, 2).toString()); 
		hash_map.put("type", table.getModel().getValueAt(row, 4).toString()); 
		
		System.out.println(value.toString());
		
		if (value.toString().equals(new String("Edit")))
		{
			this.getController().updateEmployeeForm(hash_map);
			
		}
		
		if (value.toString().equals(new String("Delete")))
		{
			this.getController().deleteEmployeeForm(hash_map);
			
		}
		
		

	    isPushed = true;
	    return button;
	  }
	  
	  /*
	  public Object getCellEditorValue() {
	    if (isPushed)  {
	      //JOptionPane.showMessageDialog(button ,label + ": Ouch!");
	      	
	      System.out.println(label + ": Ouch!");
	    }
	    isPushed = false;
	    return new String( label ) ;
	  }*/
	    
	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }
	  
	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }

	public EmployeeController getController() {
		return controller;
	}

	public void setController(EmployeeController controller) {
		this.controller = controller;
	}
	}

