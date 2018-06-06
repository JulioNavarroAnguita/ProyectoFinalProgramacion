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

import modelo.ExceptionPelicula;
import modelo.LeerCSV;
import modelo.PeliculaDAO;
import modelo.PeliculaDAOImp;
import modelo.PeliculaDTO;
import modelo.TablaModel;
import vista.Vista;

public class Controlador implements ActionListener {

	private Vista vista;
	private LeerCSV leerFichero;
	private List<PeliculaDTO> listaPePelicula;
	private int contador = 0;
	private String path;
	PeliculaDAO manipulacion = new PeliculaDAOImp();
	JTable jTable;
	
	private static Object[][] data;

	public Controlador(){}
	
	public Controlador(Vista vista) {
		this.vista = vista;
		registrarComponentes();
		
		if(manipulacion.comprobarExistenDatos()) 
			completarArrays(manipulacion.listarPeliculas());
		
		else
			completarArrays(leerFichero.getListaPeliculas());
	}
	

	public Object[][] getData() {
		return data;
	}
	
	private void registrarComponentes() {
		vista.getBtnActualizar().addActionListener(this);
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
				int row = jTable.getSelectionModel().getMinSelectionIndex();
				((TablaModel) jTable.getModel()).deleteRow(row);
				//System.out.println("pulsado " + textoBoton);

				break;
			case "Insertar":
				System.out.println("pulsado " + textoBoton);

				break;
			case "Actualizar":
				//System.out.println("pulsado " + textoBoton);


				break;

			default:
				break;
			}

		}
	}

	private void lanzarEleccionFichero() {
		JFileChooser jFileChooser = new JFileChooser("./Ficheros");
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

		TablaModel dm = new TablaModel(leerFichero);
		jTable = new JTable(dm);
		vista.getScrollPane().setViewportView(jTable);

	}
	private void activarDesactivarBotones() {
		vista.getButtonIzquierda().setEnabled(true);
		vista.getBtnNewDerecha10().setEnabled(true);
		vista.getButtonDerecha().setEnabled(true);
		vista.getButtonIzquierda10().setEnabled(true);
		vista.getBtnActualizar().setEnabled(true);
		vista.getBtnBorrar().setEnabled(true);
		vista.getBtnInsertar().setEnabled(true);
		vista.getMenuItemAcercaDe().setEnabled(false);
		vista.getMenuItemCargarDatos().setEnabled(false);

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
	
	private void desplegarInformacion() {
		JOptionPane jpJOptionPane = new JOptionPane();
		jpJOptionPane.showMessageDialog(vista.getFrame(), 
				"Creado por Julio Navarro Anguita", "Informaci�n Autor",
				JOptionPane.INFORMATION_MESSAGE);

	}
	private void salirAplicacion() {
		System.exit(0);

	}


}
