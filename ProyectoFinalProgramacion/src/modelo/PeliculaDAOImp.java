package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAOImp implements PeliculaDAO {

	private static Connection conexion = Conexion.getConexion();
	private String[] header = {"codigo", "pelicula", "director", "genero"};
	private String[][] pintarTablas;
	
	
	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String[][] getPintarTablas() {
		return pintarTablas;
	}

	public void setPintarTablas(String[][] pintarTablas) {
		this.pintarTablas = pintarTablas;
	}

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

			System.out.println("Total de peliculas: " + listaPeliculas.size());

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

			System.out.println("Peliculas borradas: " + peliculasBorradas);
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

			System.out.println("Peliculas actualizadas: " + peliculasActualizadas);
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
			for (PeliculaDTO peliculaDTO : listaPeliculas) {
				/*pst.setString(1, peliculaDTO.getCodigo());
				pst.setString(2, peliculaDTO.getPelicula());
				pst.setString(3, peliculaDTO.getDirector());
				pst.setString(4, peliculaDTO.getGenero());*/
				contador++;
			//	pst.executeUpdate();*/
				insertarPelicula(peliculaDTO);
				
				System.out.println("Peliculas insertada: " + contador);

			}




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
		String sql = "UPDATE pelicula SET pelicula = ?, director = ?, genero = ? WHERE codigo = ?);";

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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean comprobarExisteTabla() {
		
		return false;
	}


}
