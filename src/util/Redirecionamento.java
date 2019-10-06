package util;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class Redirecionamento {
	 public static void redirecionarParaPagina(String nomePagina) {
	        FacesContext context  = FacesContext.getCurrentInstance();
	        String url = context.getExternalContext().getRequestContextPath();
	        try {
				context.getExternalContext().redirect(url + "/pages/" + nomePagina + ".xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
}
