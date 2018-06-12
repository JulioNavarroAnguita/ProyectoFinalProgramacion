package modelo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class CreadorLogs {

	private static File file = new File("Logs/Logs.txt");

	
	public void crearLog(String log, String primaryKey){

		try(PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file,true)));) {
			output.printf("%-28s%-44s%-1s\n", LocalDateTime.now(), log, primaryKey);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

