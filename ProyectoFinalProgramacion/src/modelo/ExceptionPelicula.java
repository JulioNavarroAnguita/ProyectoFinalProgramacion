package modelo;

public class ExceptionPelicula extends Exception {

	
	public ExceptionPelicula(String mensaje) {

	}

	public static boolean comprobarCodigo(String codigo) {
		boolean code = false;
		//String mensaje = "El codigo introducido no es valido";
		String regex = "[a-zA-Z0-9]{6,12}";

		if(codigo.matches(regex)) {
			code = true;
			//mensaje = "El codigo introducido es correcto";
			//Logs.crearLog("ERROR BORRADO REGISTRO: codigo incorrecto " + codigo);
		}
		//System.out.println(mensaje);
		return code;

	}

}
