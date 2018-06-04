package modelo;

import javax.swing.table.AbstractTableModel;



public class TablaModel extends AbstractTableModel {

	String[] cabecera;
	Object[][] datos;
	
	public String[] getCabecera() {
		return cabecera;
	}
	public Object[][] getDatos() {
		return datos;
	}
	
	
	public TablaModel(LeerCSV leerFichero) {
		cabecera = leerFichero.getDatosCsv();
		datos = leerFichero.getData();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datos.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cabecera.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return datos[row][col];
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cabecera[column];
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex < 1)
			return false;
		return true;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		System.out.println(aValue);
		System.out.println(datos[rowIndex][columnIndex]);
		
		
	}

	
}
