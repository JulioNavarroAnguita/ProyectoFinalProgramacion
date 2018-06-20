package modelo;

import java.util.List;

public interface PeliculaDAO {
	
	List<PeliculaDTO> listarPeliculas();
	boolean borrarPeliculas(List<PeliculaDTO> listaPeliculas);
	boolean actualizarPeliculas(List<PeliculaDTO> listaPeliculas);
	boolean insertarPeliculas(List<PeliculaDTO> listaPeliculas);
	boolean insertarPelicula(PeliculaDTO pelicula);
	boolean actualizarPellicula(PeliculaDTO pelicula);
	boolean borrarPelicula(PeliculaDTO pelicula);
	void crearTabla();
	boolean comprobarExistenDatos();
	
	
}

