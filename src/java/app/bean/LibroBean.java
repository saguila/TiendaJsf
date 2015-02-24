package app.bean;

import app.modelo.Libro;
import app.modelo.LibrosExteption;
import app.negocio.GestionLibreria;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "libroBean")
@RequestScoped
public class LibroBean {

    private int ID;
    private String titulo;
    private String autor;
    private String editorial;
    private String isbn;
    private int publicacion;
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Libro> getLista() {
        return lista;
    }

    public void setLista(List<Libro> lista) {
        this.lista = lista;
    }
    private double precio;
    private String descripcion;
    private String mensaje;
    private Libro encontrado;
    private List<Libro> lista;

    public Libro getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Libro encontrado) {
        this.encontrado = encontrado;
    }

    public String alta() {
        try {
            new GestionLibreria().altaLibro(new Libro(ID, titulo, autor, editorial, isbn, publicacion, precio, descripcion, imagen));
            mensaje = "Dado de alta con exito";
        } catch (LibrosExteption ex) {
            mensaje = "Error al dar de alta";
        }
        return "mostrarMensaje";
    }

    public String baja() {
        try {
            mensaje = "Dado de baja con exito";
            new GestionLibreria().eliminarLibro(ID);
        } catch (LibrosExteption ex) {
            mensaje = "Error al dar de baja";
        }
        return "mostrarMensaje";
    }

    public String buscarTitulo() {
        lista = new ArrayList<>();
        lista = new GestionLibreria().consultarTitulo(titulo);
        return "mostrarTodos";
    }

    public String buscarISBN() {
        try {
            encontrado = new GestionLibreria().consultarISBN(isbn);
        } catch (LibrosExteption ex) {
            mensaje = "no se ha encontrado";
        }
        return "mostrarLibro";
    }

    public String modificar() {
        try {
            new GestionLibreria().modificarPrecio(isbn, precio);
            mensaje = "modificado con exito";
        } catch (LibrosExteption ex) {
            mensaje = "error al modificar";
        }
        return "mostrarMensaje";
    }

    public List<Libro> buscaTitulo() {
        lista = new GestionLibreria().consultarTitulo(titulo);
        return lista;
    }

    public String buscar() {
        try {
            encontrado = new GestionLibreria().dameLibro(ID);
        } catch (LibrosExteption ex) {
            mensaje = "no se han encontrado";
        }
        return "mostrarLibro";
    }

    public List<Libro> consultarTodos() {
        lista = new GestionLibreria().consultarTodos();
        return lista;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}