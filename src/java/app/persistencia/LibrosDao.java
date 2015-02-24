package app.persistencia;

import app.modelo.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrosDao implements IntfLibrosDao {

    private Connection conexion;

    private void abrirConexion() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conexion = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/LIBRERIA",
                    "curso", "curso");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean altaLibro(Libro libro) throws LibrosExteption {
      boolean completado = false;
        try {
            abrirConexion();
            String query = "insert into CURSO.LIBROS values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setInt(1, libro.getID());
            stm.setString(2, libro.getTitulo());
            stm.setString(3, libro.getAutor());
            stm.setString(4, libro.getEditorial());
            stm.setString(5, libro.getIsbn());
            stm.setInt(6, libro.getPublicacion());
            stm.setDouble(7, libro.getPrecio());
            stm.setString(8, libro.getDescripcion());
            stm.setString(9, libro.getImagen());
            int registrosActualizados = stm.executeUpdate();
            if (registrosActualizados == 0) {
                throw new LibrosExteption("No se ha podido dar de alta el libro.");
            }
completado = true;
        } catch (SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return completado;
    }

    @Override
    public boolean eliminarLibro(int id) throws LibrosExteption {
        boolean completado = false;
        try {
            abrirConexion();
            String query = "delete FROM CURSO.LIBROS where ID=?";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setInt(1, id);
            int registrosActualizados = stm.executeUpdate();
            if (registrosActualizados == 0) {
                throw new LibrosExteption("No se pudo dar de baja");
            }
            completado = true;
        } catch (SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return completado;
    }

    @Override
    public List<Libro> consultarTodos() {
        List<Libro> lista = new ArrayList();
        abrirConexion();
        String query = "select * from CURSO.LIBROS";
        try {
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                lista.add(new Libro(rs.getInt("ID"), rs.getString("TITULO"), rs.getString("AUTOR"),
                        rs.getString("EDITORIAL"), rs.getString("ISBN"), rs.getInt("PUBLICACION"),
                        rs.getDouble("PRECIO"), rs.getString("DESCRIPCION"),rs.getString("IMAGEN")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    @Override
    public Libro consultarISBN(String isbn) throws LibrosExteption {
        Libro encontrado = null;
        try {
            abrirConexion();
            String query = "select * from CURSO.LIBROS where ISBN=?";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, isbn);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
            encontrado = new Libro(rs.getInt("ID"), rs.getString("TITULO"), rs.getString("AUTOR"),
                    rs.getString("EDITORIAL"), rs.getString("ISBN"), rs.getInt("PUBLICACION"),
                    rs.getDouble("PRECIO"), rs.getString("DESCRIPCION"),rs.getString("IMAGEN"));
            }
            if (encontrado == null) {
                throw new LibrosExteption("No se ha encontrado el libro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return encontrado;
    }

    @Override
    public List<Libro> consultarTitulo(String titulo){
        List<Libro> lista = new ArrayList();
        try {
            abrirConexion();
            String query = "select * from CURSO.LIBROS where TITULO like?";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1,"%"+ titulo + "%");
          ResultSet rs =  stm.executeQuery();
          while(rs.next()){
              lista.add(new Libro(rs.getInt("ID"), rs.getString("TITULO"), rs.getString("AUTOR"),
                        rs.getString("EDITORIAL"), rs.getString("ISBN"), rs.getInt("PUBLICACION"),
                        rs.getDouble("PRECIO"), rs.getString("DESCRIPCION"),rs.getString("IMAGEN")));
          }
        } catch (SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    @Override
    public boolean modificarPrecio(String isbn, double precio) throws LibrosExteption {
       boolean completado = false;
        try {
            abrirConexion();
            String query = "update CURSO.LIBROS set PRECIO=? where ISBN=?";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setDouble(1, precio);
            stm.setString(2, isbn);
            int registrosActualizados = stm.executeUpdate();
            if (registrosActualizados == 0){
                throw new LibrosExteption("Error al modificar el precio");
            }
            completado = true;
        } catch (SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new LibrosExteption("Error en la query");
        } finally {
            cerrarConexion();
        }
        return completado;
    }

   public Libro dameLibro(int id) throws LibrosExteption{
       Libro libro = null;
        try {
            abrirConexion();
            String query = "select * from CURSO.LIBROS where ID=?";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setDouble(1,id);
           ResultSet rs = stm.executeQuery();
           if(rs.next()){
                             libro = new Libro(rs.getInt("ID"), rs.getString("TITULO"), rs.getString("AUTOR"),
                        rs.getString("EDITORIAL"), rs.getString("ISBN"), rs.getInt("PUBLICACION"),
                        rs.getDouble("PRECIO"), rs.getString("DESCRIPCION"),rs.getString("IMAGEN"));
           }
           else throw new LibrosExteption("No se ha encontrado el libro con esa id");
        } catch (SQLException ex) {
            Logger.getLogger(LibrosDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new LibrosExteption("Error en la query");
        } finally {
            cerrarConexion();
        }
        return libro;
   }
}
