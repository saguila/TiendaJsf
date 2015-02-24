package app.modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

  public boolean setCookie(String name, String value, int expiry) {
boolean hay = true;
    FacesContext facesContext = FacesContext.getCurrentInstance();

    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    Cookie cookie = null;

    Cookie[] userCookies = request.getCookies();
    if (userCookies != null && userCookies.length > 0 ) {
        for (int i = 0; i < userCookies.length; i++) {
            if (userCookies[i].getName().equals(name)) {
                cookie = userCookies[i];
                break;
            }
        }
    }

    if (cookie != null) {
        cookie.setValue(value);
        
    } else {
        cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath());
    }

    cookie.setMaxAge(expiry);

    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    response.addCookie(cookie);
    return hay;
  }

  public Cookie getCookie(String name) {

    FacesContext facesContext = FacesContext.getCurrentInstance();

    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    Cookie cookie = null;

    Cookie[] userCookies = request.getCookies();
    if (userCookies != null && userCookies.length > 0 ) {
        for (int i = 0; i < userCookies.length; i++) {
            if (userCookies[i].getName().equals(name)) {
                cookie = userCookies[i];
                return cookie;
            }
        }
    }
    return null;
  }
}

