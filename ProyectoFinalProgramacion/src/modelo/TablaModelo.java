package modelo;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

public class TablaModelo extends DefaultTableModel {

	Controlador controlador = new Controlador();
	String[] cabecera;
	Object[][] datos;
	PeliculaDAOImp manipulando = new PeliculaDAOImp();
	
	public TablaModelo(Object[][] datos, String[] cabecera) {
		this.cabecera = cabecera;
		this.datos  = datos;
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
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex < 1)
			return false;
		return true;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		datos[rowIndex][columnIndex] = aValue;
		System.out.println(aValue);
		//controlador.actualizarPeliculaTabla();
		fireTableCellUpdated(rowIndex, columnIndex);
		String codigo = (String) datos[rowIndex][0];
		String pelicula = (String) datos[rowIndex][1];
		String director = (String) datos[rowIndex][2];
		String genero = (String) datos[rowIndex][3];
		
		try {
			PeliculaDTO peliculaUpdate = new PeliculaDTO(codigo, pelicula, director, genero);
			manipulando.actualizarPellicula(peliculaUpdate);
			
			
			
		} catch (ExceptionPelicula e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		super.removeRow(row);
	}
	
}
