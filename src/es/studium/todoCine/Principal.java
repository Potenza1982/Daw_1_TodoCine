package es.studium.todoCine;

public class Principal 
{
    public static void main(String[] args) 
    {
        // Crear una instancia del Modelo (puede ser opcional si no se requiere inicializar nada en el modelo).
        Modelo modelo = new Modelo();

        // Crear una instancia de la Vista (en este caso, MenuPrincipal).
        MenuPrincipal menuPrincipal = new MenuPrincipal();

        // Crear una instancia del Controlador y pasarle el modelo y la vista. Aquí he metido la lócica del programa.
        new Controlador(modelo, menuPrincipal);
    }
}


