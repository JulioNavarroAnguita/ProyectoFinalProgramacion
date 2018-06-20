package modelo;

public class ExceptionPelicula extends Exception {

	
	public ExceptionPelicula(String mensaje) {

	}

	public static boolean comprobarCodigo(String codigo) {
		boolean code = false;
		String regex = "[a-zA-Z0-9]{6,12}";

		if(codigo.matches(regex)) {
			code = true;
			
		}

		return code;

	}

}
