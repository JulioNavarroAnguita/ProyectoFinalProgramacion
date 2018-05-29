package main;

import java.util.List;

import modelo.LeerCSV;
import modelo.PeliculaDTO;


public class Principal {

	public static void main(String[] args) {
		
		LeerCSV load = new LeerCSV("Ficheros/peliculas.csv");
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
}
