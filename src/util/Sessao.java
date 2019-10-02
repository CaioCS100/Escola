package util;

import javax.faces.context.FacesContext;

public class Sessao {
	
	public static void adicionarObjetoNaSessao(String nomeObjetoSessao, Object object)
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nomeObjetoSessao, object);
	}
	
	public static Object recuperarObjetoDaSessao(String nomeObjetoSessao)
	{
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(nomeObjetoSessao);
	}
	
	public static void removerObjetoDaSessao(String nomeObjetoSessao)
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
