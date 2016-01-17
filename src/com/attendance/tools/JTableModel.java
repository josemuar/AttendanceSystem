package com.attendance.tools;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class JTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] {"First Name", "Last Name", "Email", "Tag", "Type", "Edit", "Delete"};
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class, String.class, String.class, String.class,  JButton.class, JButton.class};
	public   String[][] DATA;
	//private ArrayList<Object> data = new ArrayList<Object>();
	
	public JTableModel(int rows )
	{
		
		DATA = new String[ rows ][ 7 ];
	}
	
	
	/*
	 * @addRow: Add a row to the table.
	 * @param row: the String array whit the data
	*/
	public void  addRow (String[] row, int index )
	{
		
		//Insert the data in the index specified
		DATA[ index ] = row;
		
	}
	
	
	@Override public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override public int getRowCount() {
		return DATA.length;
	}
	
	@Override public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }
	
	@Override public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_TYPES[columnIndex];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return true;
	}
	

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}
	
	
	@Override public Object getValueAt(final int rowIndex, final int columnIndex) {
		return DATA[rowIndex][columnIndex];
	}
	
	
}