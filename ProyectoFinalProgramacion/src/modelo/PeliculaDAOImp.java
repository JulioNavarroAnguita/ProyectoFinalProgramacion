package modelo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import vista.Vista;


public class PeliculaDAOImp implements PeliculaDAO {

	private static Connection conexion = Conexion.getConexion();

	@Override
	public List<PeliculaDTO> listarPeliculas() {

		List<PeliculaDTO> listaPeliculas = new ArrayList<>();
		String sql = "SELECT * FROM pelicula;";

		try (Statement st = conexion.createStatement(); 
				ResultSet rst = st.executeQuery(sql);){

			while(rst.next()) {
				PeliculaDTO peliculaDTO = new PeliculaDTO(rst.getString(1),
						rst.getString(2),
						rst.getString(3),
						rst.getString(4));
				listaPeliculas.add(peliculaDTO);
			}

			//System.out.println("Total de peliculas: " + listaPeliculas.size());

		} catch (SQLException | ExceptionPelicula e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaPeliculas;


	}

	@Override
	public boolean borrarPeliculas(List<PeliculaDTO> listaPeliculas) {

		boolean borrado = false;
		int peliculasBorradas = 0;
		String sql = "DELETE FROM pelicula WHERE codigo = ?;";

		try (PreparedStatement pst = conexion.prepareStatement(sql);){

			for (PeliculaDTO peliculaDTO : listaPeliculas) {
				pst.setString(1, peliculaDTO.getCodigo());

				peliculasBorradas += pst.executeUpdate();
			}

			//System.out.println("Peliculas borradas: " + peliculasBorradas);
			if(peliculasBorradas > 0)
				borrado = true;

		} catch (SQLException e) {
			System.out.println("Error al intentar borrar una pelicula/DAOImp");
		}

		return borrado;
	}


	@Override
	public boolean actualizarPeliculas(List<PeliculaDTO> listaPeliculas) {

		boolean actualizado = false;
		int peliculasActualizadas = 0;
		String sql = "UPDATE pelicula SET pelicula = ?, director = ?, genero = ? WHERE codigo = ?;";

		try (PreparedStatement pst = conexion.prepareStatement(sql);){
			for (PeliculaDTO peliculaDTO : listaPeliculas) {
				pst.setString(1, peliculaDTO.getPelicula());
				pst.setString(2, peliculaDTO.getDirector());
				pst.setString(3, peliculaDTO.getGenero());
				pst.setString(4, peliculaDTO.getCodigo());

				peliculasActualizadas += pst.executeUpdate();
			}

			//System.out.println("Peliculas actualizadas: " + peliculasActualizadas);
			if(peliculasActualizadas > 0)
				actualizado = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return actualizado;
	}

	@Override
	public boolean insertarPeliculas(List<PeliculaDTO> listaPeliculas) {

		boolean insertado = false;
		int peliculasInsertadas = 0; 

		String sql = "INSERT INTO pelicula (codigo, pelicula, director, genero) VALUES (?,?,?,?);";
		
		try (PreparedStatement pst = conexion.prepareStatement(sql);){
			
		
			int contador = 0;
			JOptionPane jp = new JOptionPane();
			jp.showMessageDialog(null, "Cargando datos espere...", 
					"Cargando datos", JOptionPane.NO_OPTION);
			//run(listaPeliculas);

			for (PeliculaDTO peliculaDTO : listaPeliculas) {

				contador++;	
			
				System.out.println(contador);
				insertarPelicula(peliculaDTO);

				//System.out.println("Cargando datos: " + contador + "%");

			}
	

			jp.showMessageDialog(null, "Registros completados \n" + "Peliculas insertadas: " + listaPeliculas.size(), "Carga de datos", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("Peliculas insertadas: " + contador);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return insertado;
	}

	@Override
	public boolean insertarPelicula(PeliculaDTO pelicula) {

		boolean insert = false;
		int peliculaInsert = 0;
		String sql = "INSERT INTO pelicula (codigo, pelicula, director, genero) VALUES (?,?,?,?);";

		try (PreparedStatement pst = conexion.prepareStatement(sql);){

			pst.setString(1, pelicula.getCodigo());
			pst.setString(2, pelicula.getPelicula());
			pst.setString(3, pelicula.getDirector());
			pst.setString(4, pelicula.getGenero());

			peliculaInsert++;
			//System.out.println(peliculaInsert);
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(peliculaInsert > 0)
			insert = true;

		return insert;
	}

	@Override
	public boolean actualizarPellicula(PeliculaDTO pelicula) {

		boolean update = false;
		int peliculaUpdate = 0;
		String sql = "UPDATE pelicula SET pelicula = ?, director = ?, genero = ? WHERE codigo = ?;";

		try (PreparedStatement pst = conexion.prepareStatement(sql);){
			pst.setString(1, pelicula.getPelicula());
			pst.setString(2, pelicula.getDirector());
			pst.setString(3, pelicula.getGenero());
			pst.setString(4, pelicula.getCodigo());

			peliculaUpdate += pst.executeUpdate(); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(peliculaUpdate > 0)
			update = true;

		return update;
	}

	@Override
	public boolean borrarPelicula(PeliculaDTO pelicula) {

		int peliculaDelete = 0;
		boolean delete = false;
		String sql = "DELETE from pelicula WHERE codigo = ?;";

		try (PreparedStatement pst = conexion.prepareStatement(sql);){
			pst.setString(1, pelicula.getCodigo());

			peliculaDelete += pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(peliculaDelete > 0)
			delete = true;

		return delete;
	}

	@Override
	public void crearTabla() {

		String sql = "DROP TABLE IF EXISTS pelicula;";
		String sql1 = "CREATE TABLE pelicula (codigo TEXT PRIMARY KEY, "
				+ "pelicula TEXT, director TEXT, genero TEXT);";

		try (Statement st = conexion.createStatement();){
			st.executeUpdate(sql);
			st.executeUpdate(sql1);
			//Al crear la tabla inserto los datos del csv
			//Primero cargo los datos del fichero para rellenar la lista
			//LeerCSV.cargarDatosFichero("Ficheros/peliculas.csv"); 
			//Posterior inserto los datos
			insertarPeliculas(LeerCSV.getListaPeliculas());
			System.out.println(LeerCSV.getListaPeliculas());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/*public void run(List<PeliculaDTO> lista) {
		barraDeProgreso.setVisible(true);
		barraDeProgreso.setMinimum(0);
		barraDeProgreso.setMaximum(lista.size());
		int contador = 0;
		try
		{
			for (PeliculaDTO peliculaDTO : lista) {
				contador++;
				barraDeProgreso.setValue(contador);
				barraDeProgreso.setVisible(true);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/





	@Override
	public boolean comprobarExistenDatos() {
		int existenDatos = 0;
		String sql = "SELECT COUNT(*) FROM pelicula;";

		try (Statement st = conexion.createStatement(); ResultSet rst = st.executeQuery(sql);){
			if(rst.getInt(1) > 0)
				existenDatos = 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existenDatos == 1;
	}
}
