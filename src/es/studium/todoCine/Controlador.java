package es.studium.todoCine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener
{
	Modelo modelo; // Referencia al modelo del programa
    MenuPrincipal menuPrincipal; // Referencia a la vista principal
    int idPeliculaSeleccionada = 1; // Identificador de la película actual

	Controlador(Modelo mo, MenuPrincipal me)
	{
		this.modelo = mo;
		this.menuPrincipal = me;

		System.out.println("Controlador instanciado"); // Mensaje de depuración

		this.menuPrincipal.addWindowListener(this); // Asignar el controlador como oyente de eventos de la ventana principal
        this.menuPrincipal.btnAnterior.addActionListener(this); // Asignar el controlador como oyente de eventos del botón "Anterior"
        this.menuPrincipal.btnSiguiente.addActionListener(this); // Asignar el controlador como oyente de eventos del botón "Siguiente"
	}

	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) 
	{
		System.exit(0);
	}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (menuPrincipal != null) {
			if (e.getSource() == menuPrincipal.btnSiguiente) { // Si se presiona el botón "Siguiente"
				System.out.println("Botón SIGUIENTE pulsado");
				// Incrementa el índice para mostrar la siguiente imagen
				menuPrincipal.indiceImagenActual++;
				if (menuPrincipal.indiceImagenActual >= menuPrincipal.rutasDeImagenes.length) {
					menuPrincipal.indiceImagenActual = 0; // Vuelve al inicio si se llega al final
				}	
				
				menuPrincipal.cargarDatosDeTablaPelicula(); // Cargar los datos de la película actual en la vista
			} else if (e.getSource() == menuPrincipal.btnAnterior) { // Si se presiona el botón "Anterior"
				System.out.println("Botón ANTERIOR pulsado"); // Mensaje de depuración
				// Decrementa el índice para mostrar la imagen anterior
				menuPrincipal.indiceImagenActual--;
				if (menuPrincipal.indiceImagenActual < 0) {
					menuPrincipal.indiceImagenActual = menuPrincipal.rutasDeImagenes.length - 1; // Retrocede al final si se está en la primera imagen
				}
				menuPrincipal.cargarDatosDeTablaPelicula(); // Cargar los datos de la película actual en la vista

			}
			// Cargar la nueva imagen
			menuPrincipal.imagenesPeliculas = menuPrincipal.herramienta.getImage(menuPrincipal.rutasDeImagenes[menuPrincipal.indiceImagenActual]);
			// Vuelve a pintar la ventana para mostrar la nueva imagen
			menuPrincipal.repaint();
		}
	}
}