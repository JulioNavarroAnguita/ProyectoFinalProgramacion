package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;



public class TablaModel extends AbstractTableModel{

	String[] cabecera;
	Object[][] datos;
	PeliculaDTO peliculaDTO;
	PeliculaDAOImp pDaoImp = new PeliculaDAOImp();
	
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
		datos[rowIndex][columnIndex] = aValue;
		System.out.println(aValue);
		System.out.println(datos[rowIndex][columnIndex]);
		System.out.println(aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
		String codigo = (String) datos[rowIndex][0];
		String pelicula = (String) datos[rowIndex][1];
		String director = (String) datos[rowIndex][2];
		String genero = (String) datos[rowIndex][3];
		
		try {
			List<PeliculaDTO> listaPeliculasActualizadas = new ArrayList<>();
			listaPeliculasActualizadas.add(new PeliculaDTO(codigo, pelicula, director, genero));
			pDaoImp.actualizarPeliculas(listaPeliculasActualizadas);
			
			
			
		} catch (ExceptionPelicula e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void deleteRow(int row) {
		//List<PeliculaDTO> borrarLista = new ArrayList<>();
		
		for (int i = row; i < getRowCount(); i++) {
			datos[i][0] = datos[i+1][0];
			datos[i][1] = datos[i+1][1];
			datos[i][2] = datos[i+1][2];
			datos[i][3] = datos[i+1][3];
			
		}
		//pDaoImp.borrarPelicula(
		fireTableDataChanged();
	}
	
	
	/*@Override
	public void tableChanged(TableModelEvent e) {

		int row = e.getFirstRow();
		int col = e.getColumn();
		TablaModel model = (TablaModel)e.getSource();
		
		System.out.println(datos[row][col]);
		

		
	}*/

	
}
