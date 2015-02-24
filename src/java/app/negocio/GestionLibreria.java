package app.negocio;

import app.modelo.Libro;
import app.modelo.LibrosExteption;
import app.persistencia.LibrosDao;
import java.util.List;

public class GestionLibreria implements IntfGestionLibreria {

    @Override
    public boolean altaLibro(Libro libro) throws LibrosExteption {
        return new LibrosDao().altaLibro(libro);
    }

    @Override
    public boolean eliminarLibro(int id) throws LibrosExteption {
        return new LibrosDao().eliminarLibro(id);
    }

    @Override
    public List<Libro> consultarTodos() {
        return new LibrosDao().consultarTodos();
    }

    @Override
    public Libro consultarISBN(String isbn) throws LibrosExteption {
        return new LibrosDao().consultarISBN(isbn);
    }

    @Override
    public List<Libro> consultarTitulo(String titulo){
        return new LibrosDao().consultarTitulo(titulo);
    }
public Libro dameLibro(int id) throws LibrosExteption{
    return new LibrosDao().dameLibro(id);
}
    @Override
    public boolean modificarPrecio(String isbn, double precio) throws LibrosExteption {
        return new LibrosDao().modificarPrecio(isbn, precio);
    }

}
