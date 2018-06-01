package main;

import java.awt.EventQueue;
import java.util.List;

import controlador.Controlador;
import modelo.LeerCSV;
import modelo.PeliculaDTO;
import vista.Vista;


public class Principal {



	//LeerCSV load = new LeerCSV("Ficheros/peliculas.csv");

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

	/*List<PeliculaDTO> lista = load.getListaPeliculas();
		for (PeliculaDTO peliculaDTO : lista) {
			System.out.println(peliculaDTO.getPelicula());

		}*/

	//System.out.println(load.getListaAnimales().size());
	//PeliculaDAO manipulacionPeliculas = new PeliculaDAOimp();
	//manipulacionPeliculas.listarTodasPeliculas();

	//System.out.println("Lista ordenada por nombre");
	//System.out.println(manipulacionPeliculas.listarPeliculasOrdenadasPorAnno());


	/*try {
			PeliculaDTO peliculaDTO = new PeliculaDTO("Titanic", "Amor", "EEUU", 1987, "James Cameron");
			System.out.println(peliculaDTO);
		} catch (ExceptionGenero e) {
			System.out.println("No se puede crear una pelicula de este genero");
		}*/




}

