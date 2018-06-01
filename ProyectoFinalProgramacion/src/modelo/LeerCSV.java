package modelo;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class LeerCSV {

	private static List<PeliculaDTO> listaPeliculas = new ArrayList<>();
	private static String[] datosCsv;
	private static Object[][] data;
	
	

	public String[] datosCsv() {
		return datosCsv;
	}

	public Object[][] getData() {
		return data;
	}

	public static List<PeliculaDTO> getListaPeliculas() {
		return listaPeliculas;
	}

	public LeerCSV(String fichero) {
		cargarDatosFichero(fichero);
	}

	//metodo para cargar datos pasandole como argumento un fichero
	public static List<PeliculaDTO> cargarDatosFichero(String fichero) {

		//Utilizando la clase externa CSVReader


		try (CSVReader csvReader = new CSVReader(new FileReader(fichero));){

			datosCsv = csvReader.readNext();//cabecera
			System.out.println(Arrays.toString(datosCsv));
			while((datosCsv = csvReader.readNext()) != null) {

				listaPeliculas.add(new PeliculaDTO(datosCsv[0], datosCsv[1], datosCsv[2], 
						datosCsv[3]));
				completarArrays();
			}

			//System.out.println(listaPeliculas);
		} catch (IOException | ExceptionPelicula e) {

		}

		return listaPeliculas;
	}

	private static void completarArrays() {
		data = new Object[listaPeliculas.size()][4];
		int contador = 0;
		for (PeliculaDTO pelicula: listaPeliculas) {
			data[contador][0] = pelicula.getCodigo();
			data[contador][1] = pelicula.getPelicula();
			data[contador][2] = pelicula.getDirector();
			data[contador][3] = pelicula.getGenero();

			contador++;
		}
		
	}
}


