package app.bean;

import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class IdiomaBean{
    private String locale = "es";

    public void cambiarIdioma(String idioma) {
        locale = idioma;
        //Recuperar contexto de JSF
        FacesContext context = FacesContext.getCurrentInstance();
        //Acceder a la raiz del arbol de comoponentes
        //cambiamos el locale
        context.getViewRoot().setLocale(new Locale(idioma));
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
