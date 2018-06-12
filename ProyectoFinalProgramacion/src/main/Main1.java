package main;

import java.util.List;
import java.util.Scanner;

import javax.swing.JProgressBar;

import modelo.ExceptionPelicula;
import modelo.LeerCSV;
import modelo.PeliculaDAO;
import modelo.PeliculaDAOImp;
import modelo.PeliculaDTO;


public class Main1 {

	public static void main(String[] args) {

		/*JProgressBar jp = new JProgressBar();
		
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setVisible(true);
		int contador = 0;
		for (int i = 0; i < 100; i++) {
			contador++;
			System.out.println(contador);
			jp.setValue(contador);
		}
	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		/*LeerCSV.cargarDatosFichero("Ficheros/peliculas.csv");
		
		PeliculaDAO manipulacion = new PeliculaDAOImp();
		List<PeliculaDTO> listaPelicula = LeerCSV.getListaPeliculas();

		manipulacion.crearTabla();
		manipulacion.insertarPeliculas(listaPelicula);
		//System.out.println(manipulacion.listarPeliculas());*/

		//https://www.youtube.com/watch?v=tvyRvF6-j94


	}

}
