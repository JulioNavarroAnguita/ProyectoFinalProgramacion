package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.PeliculaDAOImp;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class Vista {

	private JFrame frame;
	private JButton btnInsertar;
	private JButton btnBorrar;
	private JMenu mnFicheros;
	private JMenu mnInfo;
	private JMenuItem menuItemAcercaDe;
	private JMenuItem menuItemCargarDatos;
	private JMenuItem menuItemSalir;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JPanel panelFormulario;
	private JLabel lblCodigo;
	private JLabel lblPelcula;
	private JLabel lblDirector;
	private JLabel lblGenero;
	private JTextField textFieldCodigo;
	private JTextField textFieldPelicula;
	private JTextField textFieldDirector;
	private JTextField textFieldGenero;
	PeliculaDAOImp pImp = new PeliculaDAOImp();
	
	
	public Vista() {
		initialize();
	}




	public JFrame getFrame() {
		return frame;
	}

	public JButton getBtnInsertar() {
		return btnInsertar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JMenuItem getMenuItemAcercaDe() {
		return menuItemAcercaDe;
	}

	public JMenuItem getMenuItemCargarDatos() {
		return menuItemCargarDatos;
	}

	public JMenuItem getMenuItemSalir() {
		return menuItemSalir;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}



	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);


		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnFicheros = new JMenu("Cargar");
		menuBar.add(mnFicheros);

		menuItemCargarDatos = new JMenuItem("Cargar datos");
		mnFicheros.add(menuItemCargarDatos);

		menuItemSalir = new JMenuItem("Salir");
		mnFicheros.add(menuItemSalir);

		mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);

		menuItemAcercaDe = new JMenuItem("Acerca de");
		mnInfo.add(menuItemAcercaDe);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnBorrar = new JButton("Borrar");
		panel.add(btnBorrar);

		btnInsertar = new JButton("Insertar");
		panel.add(btnInsertar);
		btnInsertar.setEnabled(false);

		btnBorrar.setEnabled(false);


		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.25);
		splitPane.setDividerSize(10);


		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		splitPane.setRightComponent(scrollPane);

		panelFormulario = new JPanel();
		splitPane.setLeftComponent(panelFormulario);
		panelFormulario.setLayout(null);

		lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(12, 30, 56, 16);
		panelFormulario.add(lblCodigo);

		textFieldCodigo = new JTextField();
		textFieldCodigo.setEnabled(false);
		textFieldCodigo.setBounds(12, 59, 180, 22);
		panelFormulario.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);

		lblPelcula = new JLabel("Película");
		lblPelcula.setBounds(230, 30, 56, 16);
		panelFormulario.add(lblPelcula);

		lblDirector = new JLabel("Director");
		lblDirector.setBounds(12, 94, 56, 16);
		panelFormulario.add(lblDirector);

		lblGenero = new JLabel("Género");
		lblGenero.setBounds(230, 94, 56, 16);
		panelFormulario.add(lblGenero);

		textFieldPelicula = new JTextField();
		textFieldPelicula.setEnabled(false);
		textFieldPelicula.setBounds(230, 59, 180, 22);
		panelFormulario.add(textFieldPelicula);
		textFieldPelicula.setColumns(10);

		textFieldDirector = new JTextField();
		textFieldDirector.setEnabled(false);
		textFieldDirector.setBounds(12, 126, 180, 22);
		panelFormulario.add(textFieldDirector);
		textFieldDirector.setColumns(10);

		textFieldGenero = new JTextField();
		textFieldGenero.setEnabled(false);
		textFieldGenero.setBounds(230, 123, 180, 22);
		panelFormulario.add(textFieldGenero);
		textFieldGenero.setColumns(10);
	}


	public JLabel getLblCodigo() {
		return lblCodigo;
	}


	public JTextField getTextFieldCodigo() {
		return textFieldCodigo;
	}


	public JLabel getLblPelcula() {
		return lblPelcula;
	}


	public JLabel getLblDirector() {
		return lblDirector;
	}


	public JLabel getLblGenero() {
		return lblGenero;
	}


	public JTextField getTextFieldPelicula() {
		return textFieldPelicula;
	}


	public JTextField getTextFieldDirector() {
		return textFieldDirector;
	}


	public JTextField getTextFieldGenero() {
		return textFieldGenero;
	}

}
