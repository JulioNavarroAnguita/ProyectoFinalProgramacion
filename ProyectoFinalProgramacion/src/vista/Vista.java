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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Vista {

	private JFrame frame;
	private JButton btnInsertar;
	private JButton btnBorrar;
	private JButton btnActualizar;
	private JButton buttonIzquierda10;
	private JButton buttonIzquierda;
	private JButton buttonDerecha;
	private JButton btnNewDerecha10;
	private JMenu mnFicheros;
	private JMenu mnInfo;
	private JMenuItem menuItemAcercaDe;
	private JMenuItem menuItemCargarDatos;
	private JMenuItem menuItemSalir;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}
	
	
	public JButton getButtonIzquierda10() {
		return buttonIzquierda10;
	}

	public JButton getButtonIzquierda() {
		return buttonIzquierda;
	}

	public JButton getButtonDerecha() {
		return buttonDerecha;
	}

	public JButton getBtnNewDerecha10() {
		return btnNewDerecha10;
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

	public JButton getBtnActualizar() {
		return btnActualizar;
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


	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 450);
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
		
		buttonIzquierda10 = new JButton("<<");
		panel.add(buttonIzquierda10);
		
		buttonIzquierda = new JButton("<");
		panel.add(buttonIzquierda);
		
		btnInsertar = new JButton("Insertar");
		panel.add(btnInsertar);
		
		btnBorrar = new JButton("Borrar");
		panel.add(btnBorrar);
		
		btnActualizar = new JButton("Actualizar");
		panel.add(btnActualizar);
		
		buttonDerecha = new JButton(">");
		panel.add(buttonDerecha);
		
		btnNewDerecha10 = new JButton(">>");
		panel.add(btnNewDerecha10);
		
		btnActualizar.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnInsertar.setEnabled(false);
		btnNewDerecha10.setEnabled(false);
		buttonDerecha.setEnabled(false);
		buttonIzquierda.setEnabled(false);
		buttonIzquierda10.setEnabled(false);
		
		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
