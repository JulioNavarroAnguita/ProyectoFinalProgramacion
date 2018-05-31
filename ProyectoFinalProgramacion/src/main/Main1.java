package main;

import modelo.LeerCSV;
import modelo.PeliculaDAO;
import modelo.PeliculaDAOImp;

public class Main1 {

	public static void main(String[] args) {
		
	//	LeerCSV.cargarDatosFichero("Ficheros/peliculas.csv");
		PeliculaDAO manipulacion = new PeliculaDAOImp();
		manipulacion.crearTabla();
		manipulacion.insertarPeliculas(LeerCSV.cargarDatosFichero("Ficheros/peliculas.csv"));
		

	}

}
