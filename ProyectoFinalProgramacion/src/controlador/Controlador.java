package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


import modelo.LeerCSV;
import modelo.PeliculaDTO;
import modelo.TablaModel;
import vista.Vista;

public class Controlador implements ActionListener, TableModelListener {

	private Vista vista;
	private LeerCSV leerFichero;
	private List<PeliculaDTO> listaPePelicula;
	private int contador = 0;
	private String path;

	public Controlador(Vista vista) {
		this.vista = vista;
		registrarComponentes();
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

	/*private void colocarFormulario(int i) {
		vista.getTextFieldName().setText(listaPersona.get(i).getNombre());
		vista.getTextFieldLastName().setText(listaPersona.get(i).getApellidos());
		vista.getTextFieldGender().setText(listaPersona.get(i).getGenero() + "");
		vista.getTextFieldLanguage().setText(listaPersona.get(i).getLenguaje());
		vista.getTextFieldRace().setText(listaPersona.get(i).getRaza());


	}*/

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
				System.out.println(menuItem.getText());
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
				//contador++;
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
			default:
				break;
			}
			//contador %= listaPePelicula.size();

			//if(contador < 0) 
				//contador += listaPePelicula.size();
			//colocarFormulario(contador);

		}
	}
	private void lanzarEleccionFichero() {
		JFileChooser jFileChooser = new JFileChooser(".");
		int resultado = jFileChooser.showOpenDialog(vista.getFrame());
		if (resultado == jFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getPath();
			leerFichero = new LeerCSV(path);
			listaPePelicula = leerFichero.getListaPeliculas();
			//colocarFormulario(contador);
			vista.getButtonIzquierda().setEnabled(true);
			vista.getBtnNewDerecha10().setEnabled(true);
			vista.getButtonDerecha().setEnabled(true);
			vista.getButtonIzquierda10().setEnabled(true);
			vista.getMenuItemAcercaDe().setEnabled(false);
			
			TablaModel mtTabla = new TablaModel(leerFichero);
			JTable jTable = new JTable(mtTabla);
			jTable.getModel().addTableModelListener(this);
			vista.getScrollPane().setViewportView(jTable);

		}

	}
	private void desplegarInformacion() {
		JOptionPane jpJOptionPane = new JOptionPane();
		jpJOptionPane.showMessageDialog(vista.getFrame(), 
				"Creado por Julio Navarro Anguita", "Información Autor",
				JOptionPane.INFORMATION_MESSAGE);

	}
	private void salirAplicacion() {
		System.exit(0);

	}
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
