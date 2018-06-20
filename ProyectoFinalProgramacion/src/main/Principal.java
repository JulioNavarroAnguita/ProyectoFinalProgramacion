package main;

import java.awt.EventQueue;
import java.util.List;

import controlador.Controlador;
import modelo.LeerCSV;
import modelo.PeliculaDTO;
import vista.Vista;



public class Principal {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista vista = new Vista();
					new Controlador(vista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}

