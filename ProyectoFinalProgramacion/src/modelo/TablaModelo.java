package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.ArrayUtils;

import controlador.Controlador;
import vista.Vista;

public class TablaModelo extends AbstractTableModel {

	Controlador controlador = new Controlador();
	String[] cabecera;
	Object[][] datos;
	Vista vista;
	PeliculaDAOImp manipulando = new PeliculaDAOImp();
	CreadorLogs log = new CreadorLogs();

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
		String logger = "ACTUALIZADO";
		String primaryKey = (String) datos[rowIndex][0];
		datos[rowIndex][columnIndex] = aValue;
		System.out.println(aValue);
		//controlador.actualizarPeliculaTabla();
		fireTableCellUpdated(rowIndex, columnIndex);
		String codigo   = (String) datos[rowIndex][0];
		String pelicula = (String) datos[rowIndex][1];
		String director = (String) datos[rowIndex][2];
		String genero   = (String) datos[rowIndex][3];

		try {
			PeliculaDTO peliculaUpdate = new PeliculaDTO(codigo, pelicula, director, genero);
			manipulando.actualizarPellicula(peliculaUpdate);
			log.crearLog(logger, primaryKey);
			JOptionPane.showConfirmDialog(null, "Pelicula actualizada", "Actualizar película", JOptionPane.DEFAULT_OPTION);

		} catch (ExceptionPelicula e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addRow(PeliculaDTO pelicula) {
		String logger = "INSERTADO";
		String primaryKey = pelicula.getCodigo();
		manipulando.insertarPelicula(pelicula);
		log.crearLog(logger, primaryKey);
		
	}



	public void borrarPelicula(int row) {

		String logger = "BORRADO";
		String primaryKey = (String) datos[row][0];
		int eleccion = JOptionPane.showConfirmDialog(null, "Se va a eliminar esta película ¿estás deacuerdo?", "Eliminar película", JOptionPane.OK_CANCEL_OPTION);
		if(eleccion == JOptionPane.OK_OPTION) {

			try {
				PeliculaDTO peliculaBorrada = new PeliculaDTO((String)datos[row][0], 
						(String)datos[row][1], (String)datos[row][2], (String)datos[row][3]);

				datos = ArrayUtils.remove(datos, row);
				manipulando.borrarPelicula(peliculaBorrada);
				log.crearLog(logger, primaryKey);
				fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Pelicula borrada correctamente", "Pelicula eliminada", JOptionPane.NO_OPTION);

			} catch (ExceptionPelicula e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
