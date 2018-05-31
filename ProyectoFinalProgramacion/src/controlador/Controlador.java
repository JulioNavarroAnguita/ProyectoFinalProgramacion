package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Vista;

public class Controlador implements ActionListener {

	private Vista vista;
	
	public Controlador(Vista vista) {
		this.vista = vista;
		registrarComponentes();
	}
	private void registrarComponentes() {
		vista.getBtnActualizar().addActionListener(this);
		vista.getBtnBorrar().addActionListener(this);
		vista.getBtnInsertar().addActionListener(this);
		vista.getBtnBorrar().addActionListener(this);
		vista.getBtnSalir().addActionListener(this);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		

	}

}
