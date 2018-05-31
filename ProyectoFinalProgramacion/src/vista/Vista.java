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

public class Vista {

	private JFrame frame;
	private JButton btnInsertar;
	private JButton btnBorrar;
	private JButton btnActualizar;
	private JButton btnSalir;

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

	public JButton getBtnInsertar() {
		return btnInsertar;
	}



	public JButton getBtnBorrar() {
		return btnBorrar;
	}



	public JButton getBtnActualizar() {
		return btnActualizar;
	}



	public JButton getBtnSalir() {
		return btnSalir;
	}



	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFicheros = new JMenu("Cargar");
		menuBar.add(mnFicheros);
		
		JMenuItem mntmCargarDatos = new JMenuItem("Cargar datos");
		mnFicheros.add(mntmCargarDatos);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnFicheros.add(mntmSalir);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnSobre.add(mntmSobre);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnInsertar = new JButton("Insertar");
		panel.add(btnInsertar);
		
		btnBorrar = new JButton("Borrar");
		panel.add(btnBorrar);
		
		btnActualizar = new JButton("Actualizar");
		panel.add(btnActualizar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnSalir);
	}

}
