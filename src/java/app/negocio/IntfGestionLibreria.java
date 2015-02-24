package app.negocio;

import app.modelo.Libro;
import app.modelo.LibrosExteption;
import java.util.List;

public interface IntfGestionLibreria {

    public boolean altaLibro(Libro libro) throws LibrosExteption;

    public boolean eliminarLibro(int id) throws LibrosExteption;

    public List<Libro> consultarTodos();

    public Libro consultarISBN(String isbn) throws LibrosExteption;

    public List<Libro> consultarTitulo(String titulo);

    public boolean modificarPrecio(String isbn, double precio) throws LibrosExteption;
}
