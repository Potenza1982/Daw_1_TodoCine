package es.studium.todoCine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos 
{
	String driver = "com.mysql.cj.jdbc.Driver"; // Controlador JDBC para MySQL
	String url = "jdbc:mysql://localhost:3306/todocine"; // URL de la base de datos
	String userDB = "root"; // Usuario de la base de datos
	String passwordDB = "Studium2023"; // Contraseña del usuario

	Connection connection = null; // Conexión a la base de datos
	PreparedStatement preparedStatement = null; // Sentencia SQL preparada
	ResultSet resultSet = null; // Resultado de la consulta

	// Método para establecer la conexión a la base de datos
	public void conectar() 
	{
		try 
		{
			// Cargar el controlador JDBC
			Class.forName(driver);

			// Establecer la conexión con la base de datos
			connection = DriverManager.getConnection(url, userDB, passwordDB);
		} 
		catch (Exception e) 
		{
			System.out.println("Error: " + e.getMessage());
		}
	}

	// Método para cerrar la conexión a la base de datos
	public void desconectar() 
	{
		try 
		{
			if (connection != null) 
			{
				connection.close();
			}
		} 
		catch (SQLException e) 
		{
			// Manejar errores al cerrar la conexión
		}
	}

	// Método para cargar los datos de la tabla "pelicula" basados en un índice
	public String cargarDatosDeTablaPelicula(int indiceImagenActual) 
	{
		conectar();
		
		// Nos aseguramos de que la variable tenga un valor inicial, se utilizará para almacenar 
		// los datos de una película a partir de una consulta a la base de datos.
		String datosPelicula = null; 

		// Incrementa el índice para obtener datos basados en la imagen actual
		indiceImagenActual = indiceImagenActual + 1;

		try 
		{
			Connection connection = getConnection();
			// Preparar una consulta SQL para seleccionar una película por su ID
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM pelicula where idPelicula = " + indiceImagenActual);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) 
			{
				int idPelicula = resultSet.getInt("idPelicula");
				String tituloPelicula = resultSet.getString("tituloPelicula");
				int anioPelicula = resultSet.getInt("anioPelicula");
				String direccionPelicula = resultSet.getString("direccionPelicula");
				String repartoPelicula = resultSet.getString("repartoPelicula");
				String generoPelicula = resultSet.getString("generoPelicula");
				String sinopsisPelicula = resultSet.getString("sinopsisPelicula");

				// Formatea los datos de la película
				datosPelicula = String.format("Título: %s\n\nAño: %d\n\nDirector: %s\n\nReparto: %s\n\nGénero: %s\n\nSinopsis: %s\n",
						tituloPelicula, anioPelicula, direccionPelicula, repartoPelicula, generoPelicula, sinopsisPelicula);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		desconectar();

		// La instrucción return datosPelicula; se utiliza para devolver el valor contenido 
		// en la variable datosPelicula.
		return datosPelicula;
	}

	// Método para obtener la conexión actual a la base de datos
	public Connection getConnection() {
		return connection;
	}
}