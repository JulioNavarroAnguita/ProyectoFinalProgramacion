package modelo;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

public class Conexion {

	private static Connection conexion;

	public Conexion() {

		Properties properties = new Properties();
		try {
			properties.load(new FileReader("Ficheros/BD.properties"));
			String BD = properties.getProperty("BD");
			String BD_URL = properties.getProperty("BD_URL");
			String DRIVER = properties.getProperty("DRIVER");

			Class.forName(DRIVER);
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);

			conexion = DriverManager.getConnection(BD_URL + BD, config.toProperties());

		} catch (IOException | ClassNotFoundException | SQLException e) {
			System.out.println("El fichero no se encuentra/Clase Conexion");
		}

	}

	public static Connection getConexion() {
		if(conexion == null) {
			new Conexion();
			Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		}

		return conexion;
	}

	static class ShutdownHook extends Thread {

		@Override
		public void run() {

			try {
				if(conexion != null) {
					System.out.println("Cerrando la conexion");
					conexion.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	//Comprobando la conexion
	/*public static void main(String[] args) {
		Connection conexion = Conexion.getConexion();
		System.out.println(conexion);
	}*/
}
