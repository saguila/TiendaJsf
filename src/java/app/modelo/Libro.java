package app.modelo;

public class Libro {

    int ID;
    private String titulo;
    private String autor;
    private String editorial;
    private String isbn;
    private int publicacion;
    private double precio;
    private String descripcion;
    private String imagen;

    public Libro(int ID, String titulo, String autor, String editorial, String isbn, int publicacion, double precio, String descripcion, String imagen) {
        this.ID = ID;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.publicacion = publicacion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Libro() {
    }

    @Override
    public String toString() {
        return "Libro{" + "ID=" + ID + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", isbn=" + isbn + ", publicacion=" + publicacion + ", precio=" + precio + ", descripcion=" + descripcion + '}';
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
