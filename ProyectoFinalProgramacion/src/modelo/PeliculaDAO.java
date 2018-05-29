package modelo;

import java.util.List;

public interface PeliculaDAO {


	List<PeliculaDTO> listarPeliculas();
	//boolean insertarPelicula(PeliculaDTO pelicula);
	boolean borrarPeliculas(List<PeliculaDTO> listaPeliculas);
	boolean actualizarPeliculas(List<PeliculaDTO> listaPeliculas);
	boolean insertarPeliculas(List<PeliculaDTO> listaPeliculas);
}

