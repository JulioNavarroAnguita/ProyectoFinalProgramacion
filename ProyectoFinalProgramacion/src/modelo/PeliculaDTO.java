package modelo;

public class PeliculaDTO {

	private String codigo;
	private String pelicula;
	private String director;
	private String genero;
	
	public PeliculaDTO(String codigo, String pelicula, String director, String genero) throws ExceptionPelicula {
		
		if(ExceptionPelicula.comprobarCodigo(codigo)) {
			this.codigo = codigo;
			this.pelicula = pelicula;
			this.director = director;
			this.genero = genero;
			
		}else
			throw new ExceptionPelicula("El codigo introducido no es valido");
		
	}

	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getPelicula() {
		return pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "|| Pelicula || \n codigo: " + codigo + "\n pelicula: " + pelicula + "\n director: " + director + "\n genero: "
				+ genero;
	}
	
	
}
