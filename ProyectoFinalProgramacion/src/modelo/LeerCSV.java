package modelo;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class LeerCSV {

	private static List<PeliculaDTO> listaPeliculas = new ArrayList<>();


	public LeerCSV(String fichero) {
		cargarDatosFichero(fichero);
	}

	//metodo para cargar datos pasandole como argumento un fichero
	public static List<PeliculaDTO> cargarDatosFichero(String fichero) {

		//Utilizando la clase externa CSVReader


		try (CSVReader csvReader = new CSVReader(new FileReader(fichero));){

			String[] datosCsv = csvReader.readNext();
			while((datosCsv = csvReader.readNext()) != null) {

				listaPeliculas.add(new PeliculaDTO(datosCsv[0], datosCsv[1], datosCsv[2], 
						datosCsv[3]));
			}

			//System.out.println(listaPeliculas);
		} catch (IOException | ExceptionPelicula e) {

		}

		return listaPeliculas;
	}
}


