package app.modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@SessionScoped
public class Usuario {

    private String nombre;
    private String contraseña;

     public String crearCookie() {
        Cookie cookie = new Cookie("nombreUsuario", nombre);  
        cookie.setMaxAge(2 * 60 * 60);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.addCookie(cookie);
        return "index";
    }
    
    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

}
