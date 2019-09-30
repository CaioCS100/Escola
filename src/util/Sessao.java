package util;

import javax.faces.context.FacesContext;

public class Sessao {
	private final String OBJETO_SESSAO;
	
	public Sessao(String nomeObjetoSessao) {
		OBJETO_SESSAO = nomeObjetoSessao;
	}
	
	public void adicionarObjetoNaSessao(Object object)
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(OBJETO_SESSAO, object);
	}
	
	public Object recuperarObjetoDaSessao(String nomeObjetoSessao)
	{
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(nomeObjetoSessao);
	}

}
