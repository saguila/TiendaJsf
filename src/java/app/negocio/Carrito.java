package app.negocio;

import app.modelo.Libro;
import app.modelo.LibrosExteption;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "miCarro")
@SessionScoped
public class Carrito implements Serializable {

    private double importe;
    List<Libro> contenido = new ArrayList<>();

    private String mensaje;
    private boolean loogeado = false;

    public Carrito() {
    }

    public String agregarLibro(int id) throws LibrosExteption {
        // Si el usuario no esta logado le enviamos al formulario de login
        if (compruebaLogin()) {
            Libro encontrado = new GestionLibreria().dameLibro(id);
            contenido.add(encontrado);
            importe += encontrado.getPrecio();
            return "mostrarCarrito";
        } else {
            return "login";
        }
    }

    public void sacarLibro(int id) throws LibrosExteption {
        Libro encontrado = null;
        for (Libro l : contenido) {
            if (id == l.getID()) {
                encontrado = l;
            }
        }
        contenido.remove(encontrado);
        importe -= encontrado.getPrecio();
    }

    //OK
    public boolean compruebaLogin() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cookies[] = request.getCookies();
        //Buscar la cookie con el nombre del usuario
        for (Cookie c : cookies) {
            if ("nombreUsuario".equals(c.getName())) {
                loogeado = true;
            }
        }
        return loogeado;
    }

    public List<Libro> getContenido() {
        return contenido;
    }

    public double getImporte() {
        return importe;
    }

    public String getMensaje() {
        return mensaje;
    }

}
