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
	
	

	public String[] getDatosCsv() {
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
	public static void cargarDatosFichero(String fichero) {

		//Utilizando la clase externa CSVReader

		int contador = 0;
		try (CSVReader csvReader = new CSVReader(new FileReader(fichero));){
			datosCsv = csvReader.readNext();//cabecera
			String[] dataReader;
			while((dataReader = csvReader.readNext()) != null) {
				
				listaPeliculas.add(new PeliculaDTO(dataReader[0], dataReader[1], dataReader[2], 
						dataReader[3]));
				contador++;
				System.out.println("añadiendo peliculas : " + contador);
				
			}
			
			completarArrays();
			System.out.println(listaPeliculas.size());
			System.out.println("Lista añadida");
			
			//System.out.println(listaPeliculas);
		} catch (IOException | ExceptionPelicula e) {

		}

		//return listaPeliculas;

	}

	public static void completarArrays() {
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


