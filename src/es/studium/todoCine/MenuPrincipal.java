package es.studium.todoCine;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;

public class MenuPrincipal extends Frame
{
	private static final long serialVersionUID = 1L;

	Image fondo, imagenesPeliculas; // Para almacenar imágenes

	TextArea txaDatosPelis = new TextArea(); // Un área de texto para mostrar información


	// Inicializar botones
	Button btnAnterior = new Button("ANTERIOR");
	Button btnSiguiente = new Button("SIGUIENTE");

	Toolkit herramienta; // Una herramienta para cargar imágenes

	BaseDatos baseDatos; // Agrega una referencia a tu clase BaseDatos

	// Rutas de las imágenes a mostrar
	String[] rutasDeImagenes = 
		{
				"img_movies/pelicula_1.jpg",
				"img_movies/pelicula_2.jpg",
				"img_movies/pelicula_3.jpg"
		};

	int indiceImagenActual = 0; // Índice de la imagen actual

	MenuPrincipal()
	{
		System.out.println("Constructor de MenuPrincipal llamado"); // Mensaje de depuración.

		setTitle("Cartelera");
		setSize(900, 500);
		setLayout(null); // Usar un diseño nulo para posicionar manualmente los elementos
		setBackground(Color.yellow);
		setResizable(false);
		setLocationRelativeTo(null);

		// Crea un objeto Font para especificar el tipo de letra, tamaño y estilo
		Font font = new Font("Arial Rounded", Font.BOLD, 16);

		// Establece la fuente en el TextArea
		txaDatosPelis.setFont(font);

		// Establecer el color del texto
		txaDatosPelis.setForeground(Color.black);

		// Establecer las ubicaciones de los botones
		btnAnterior.setBounds(160, 430, 100, 30);
		btnSiguiente.setBounds(270, 430, 100, 30);

		// Agregar los botones al frame
		add(btnAnterior);
		add(btnSiguiente);

		// Agregar el TextArea y establecer su ubicación y dimensiones
		txaDatosPelis.setBounds(270, 70, 300, 330); // (x, y, ancho, alto)
		add(txaDatosPelis);

		// Inicializa la referencia a la clase BaseDatos
		baseDatos = new BaseDatos();

		// Carga los datos de la tabla y muéstralos en el TextArea
		cargarDatosDeTablaPelicula();

		herramienta = getToolkit();
		fondo = herramienta.getImage("img/portadaVista2.jpg");
		imagenesPeliculas = herramienta.getImage(rutasDeImagenes[indiceImagenActual]);

		setVisible(true); // Hacer la ventana visible
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g); // Llama al método de la superclase

		// Dibuja la imagen de fondo a lo largo de toda la ventana
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		// Dibuja la imagen de la película en una ubicación específica
		g.drawImage(imagenesPeliculas, 40, 70, 220, 330, this);
	}

	// Método para cargar los datos de la tabla "pelicula" y mostrarlos en el TextArea
	public void cargarDatosDeTablaPelicula() {

		String datosPelicula = baseDatos.cargarDatosDeTablaPelicula(indiceImagenActual);

		txaDatosPelis.setText(""); // Borra el contenido existente del TextArea

		txaDatosPelis.setText(datosPelicula + "\n\n"); // Muestra los datos en el TextArea
	}
}
