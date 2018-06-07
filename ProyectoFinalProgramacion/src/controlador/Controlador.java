package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.views.AbstractView;

import modelo.ExceptionPelicula;
import modelo.LeerCSV;
import modelo.PeliculaDAO;
import modelo.PeliculaDAOImp;
import modelo.PeliculaDTO;
import modelo.TablaModelo;
import vista.Vista;

public class Controlador implements ActionListener {

	private Vista vista;
	private LeerCSV leerFichero;
	private List<PeliculaDTO> listaPePelicula;
	private int contador = 0;
	DefaultTableModel dtm;


	public static void setData(Object[][] data) {
		Controlador.data = data;
	}
	private String path;
	PeliculaDAO manipulacion = new PeliculaDAOImp();
	JTable jTable;


	String[] cabecera = {"codigo","pelicula","director","genero"};
	private static Object[][] data;


	public Controlador(){}

	public Controlador(Vista vista) {
		this.vista = vista;
		registrarComponentes();



		if(manipulacion.comprobarExistenDatos()) {
			completarArrays(manipulacion.listarPeliculas());
			activarDesactivarBotones();
			pintarTabla();
			//vista.redimensionarJSPlit();


		}
		else {
			//System.out.println("Aqui entra");
			manipulacion.crearTabla();
			completarArrays(manipulacion.listarPeliculas());
		}
	}


	public Object[][] getData() {
		return data;
	}

	private void registrarComponentes() {

		vista.getBtnBorrar().addActionListener(this);
		vista.getBtnInsertar().addActionListener(this);
		vista.getBtnNewDerecha10().addActionListener(this);
		vista.getButtonIzquierda().addActionListener(this);
		vista.getButtonIzquierda10().addActionListener(this);
		vista.getButtonDerecha().addActionListener(this);
		vista.getMenuItemAcercaDe().addActionListener(this);
		vista.getMenuItemCargarDatos().addActionListener(this);
		vista.getMenuItemSalir().addActionListener(this);

	}


	@Override
	public void actionPerformed(ActionEvent e) {

		//agrupamos jmenuitem
		if (e.getSource().getClass() == JMenuItem.class) {
			JMenuItem menuItem = (JMenuItem) e.getSource();
			//identificamos el jmenuitem que genera el evento
			if (menuItem.getText().equals("Salir"))
				salirAplicacion();
			else if (menuItem.getText().equals("Acerca de")) {
				desplegarInformacion();
			}

			else
				lanzarEleccionFichero();
		}

		if(e.getSource().getClass() == JButton.class) {
			JButton jButton = (JButton)e.getSource();
			String textoBoton = jButton.getText();
			switch (textoBoton) {
			case ">":
				System.out.println("pulsado " + textoBoton);
				//contador++;//
				//colocarFormulario(contador);
				break;
			case ">>":
				System.out.println("pulsado " + textoBoton);
				//contador += 10;
				//colocarFormulario(contador);
				break;
			case "<":
				System.out.println("pulsado " + textoBoton);
				//contador--;
				//colocarFormulario(contador);
				break;
			case "<<":
				System.out.println("pulsado " + textoBoton);
				//contador -= 10;
				//colocarFormulario(contador);
				break;
			case "Borrar":
				/*int row = jTable.getSelectionModel().getMinSelectionIndex();
				((TablaModel) jTable.getModel()).deleteRow(row);*/
				borrarPeliculaTabla();

				break;
			case "Insertar":
				//System.out.println("pulsado " + textoBoton);

				break;

			default:
				break;
			}

		}
	}

	private void lanzarEleccionFichero() {
		JFileChooser jFileChooser = new JFileChooser(".");
		int resultado = jFileChooser.showOpenDialog(vista.getFrame());
		if (resultado == jFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getPath();
			leerFichero = new LeerCSV(path);
			listaPePelicula = leerFichero.getListaPeliculas();
			activarDesactivarBotones();
			pintarTabla();


		}

	}

	private void pintarTabla() {

		dtm = new DefaultTableModel(data, cabecera);
		jTable = new JTable(dtm);
		vista.getScrollPane().setViewportView(jTable);

	}
	private void activarDesactivarBotones() {
		vista.getButtonIzquierda().setEnabled(true);
		vista.getBtnNewDerecha10().setEnabled(true);
		vista.getButtonDerecha().setEnabled(true);
		vista.getButtonIzquierda10().setEnabled(true);
		vista.getBtnBorrar().setEnabled(true);
		vista.getBtnInsertar().setEnabled(true);
		vista.getMenuItemAcercaDe().setEnabled(false);
		vista.getMenuItemCargarDatos().setEnabled(false);

		//vista.getTextFieldCodigo().setEditable(true);



	}

	public static void completarArrays(List<PeliculaDTO> lista) {
		data = new Object[lista.size()][4];
		int contador = 0;
		for (PeliculaDTO pelicula: lista) {
			data[contador][0] = pelicula.getCodigo();
			data[contador][1] = pelicula.getPelicula();
			data[contador][2] = pelicula.getDirector();
			data[contador][3] = pelicula.getGenero();

			contador++;
		}

	}

	public void borrarPeliculaTabla() {
		try {
			PeliculaDTO peliBorrar = new PeliculaDTO((String) jTable.getValueAt(jTable.getSelectedRow(), 0), 
					(String)jTable.getValueAt(jTable.getSelectedRow(), 1),
					(String)jTable.getValueAt(jTable.getSelectedRow(), 2),
					(String)jTable.getValueAt(jTable.getSelectedRow(), 3));
			dtm = (DefaultTableModel) jTable.getModel(); 
			dtm.removeRow(jTable.getSelectedRow()); 
			manipulacion.borrarPelicula(peliBorrar);

		} catch (ExceptionPelicula e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*public void actualizarPeliculaTabla() {
		try {
			
			PeliculaDTO actualizarPeliculaTabla = new PeliculaDTO((String) jTable.setValueAt(, row, column), 
					(String)jTable.getValueAt(jTable.getSelectedRow(), 1),
					(String)jTable.getValueAt(jTable.getSelectedRow(), 2),
					(String)jTable.getValueAt(jTable.getSelectedRow(), 3));
			manipulacion.actualizarPellicula(actualizarPeliculaTabla);
		} catch (ExceptionPelicula e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	private void desplegarInformacion() {
		JOptionPane jpJOptionPane = new JOptionPane();
		jpJOptionPane.showMessageDialog(vista.getFrame(), 
				"Creado por Julio Navarro Anguita", "Informaci�n Autor",
				JOptionPane.INFORMATION_MESSAGE);

	}
	private void salirAplicacion() {
		System.exit(0);

	}

	public String[] getCabecera() {

		return cabecera;
	}
}



