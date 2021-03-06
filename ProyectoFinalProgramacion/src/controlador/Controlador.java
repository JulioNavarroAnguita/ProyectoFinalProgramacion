package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
	private List<PeliculaDTO> listaPelicula;
	private int contador = 0;
	private String path;
	TablaModelo dtm;
	JTable jTable;
	PeliculaDAO manipulacion = new PeliculaDAOImp();
	static String[] cabecera = {"codigo","pelicula","director","genero"};
	static Object[][] data;


	public Controlador(){}
	
	public Controlador(Vista vista) {
		this.vista = vista;
		registrarComponentes();

	}

	public String[] getCabecera() {

		return cabecera;
	}

	public Object[][] getData() {
		return data;
	}

	private void registrarComponentes() {

		vista.getBtnBorrar().addActionListener(this);
		vista.getBtnInsertar().addActionListener(this);
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

			else {
				lanzarEleccionFichero();
				
			}
				
		}

		if(e.getSource().getClass() == JButton.class) {
			JButton jButton = (JButton)e.getSource();
			String textoBoton = jButton.getText();
			
			switch (textoBoton) {
			
				
			case "Borrar":

				int row = jTable.getSelectedRow();
				if(row < 0) {
					JOptionPane jpBorrar = new JOptionPane();
					jpBorrar.showMessageDialog(null, "No ha seleccionado ninguna pelicula", "Error de borrado", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				dtm.borrarPelicula(row);

				break;
				
			case "Insertar":
				
				try {
					String codigo   = vista.getTextFieldCodigo().getText();
					String pelicula = vista.getTextFieldPelicula().getText();
					String director = vista.getTextFieldDirector().getText();
					String genero   = vista.getTextFieldGenero().getText();
					
					PeliculaDTO peli = new PeliculaDTO(codigo, pelicula, director, genero);
	
					dtm.addRow(peli);
					actualizarTabla();
					vista.getTextFieldCodigo().setText("");
					vista.getTextFieldPelicula().setText("");
					vista.getTextFieldDirector().setText("");
					vista.getTextFieldGenero().setText("");
					System.out.println(peli);
					
				} catch (ExceptionPelicula e1) {
					JOptionPane jp = new JOptionPane();
					jp.showMessageDialog(null, "Error al insertar el c�digo.\n" + "-El c�digo debe tener entre 6-12 caracteres\n"
							+ "-No puede tener espacios en blanco\n"
							+ "-No admite car�cteres especiales",
							"C�digo no v�lido", JOptionPane.ERROR_MESSAGE);
					
				}

				break;

			default:
				break;
			}

		}
	}


	private void actualizarTabla() {
		completarArrays(manipulacion.listarPeliculas());
		pintarTabla();
		
	}

	private void lanzarEleccionFichero() {
		JFileChooser jFileChooser = new JFileChooser(".");
		int resultado = jFileChooser.showOpenDialog(vista.getFrame());
		if (resultado == jFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getPath();
			leerFichero = new LeerCSV(path);
			listaPelicula = leerFichero.getListaPeliculas();


			if(manipulacion.comprobarExistenDatos()) {
				completarArrays(manipulacion.listarPeliculas());
				//vista.redimensionarTabla();
			}else {
				manipulacion.crearTabla();
				completarArrays(manipulacion.listarPeliculas());
			}

			activarDesactivarBotones();
			pintarTabla();

		}

	}

	private void pintarTabla() {
		
		dtm = new TablaModelo(data, cabecera);
		jTable = new JTable(dtm);
		vista.getScrollPane().setViewportView(jTable);

	}
	private void activarDesactivarBotones() {
		
		vista.getBtnBorrar().setEnabled(true);
		vista.getBtnInsertar().setEnabled(true);
		vista.getMenuItemAcercaDe().setEnabled(false);
		vista.getMenuItemCargarDatos().setEnabled(false);
		vista.getTextFieldCodigo().setEnabled(true);
		vista.getTextFieldPelicula().setEnabled(true);
		vista.getTextFieldDirector().setEnabled(true);
		vista.getTextFieldGenero().setEnabled(true);


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



