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
	
	

	public String[] getDatosCsv() {
		return datosCsv;
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
				System.out.println("a�adiendo peliculas : " + contador);
				
			}

		} catch (IOException | ExceptionPelicula e) {

		}


	}

	
}


