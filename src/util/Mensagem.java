package util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensagem {
	
	public static void criarMensagem(Severity categoriaMensagem, String mensagem, String detalheAdicional)
	{
		FacesMessage message = new FacesMessage(categoriaMensagem, mensagem, detalheAdicional);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

}
