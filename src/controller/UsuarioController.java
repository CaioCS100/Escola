package controller;
import javax.faces.bean.ManagedBean;

import dao.UsuarioDAO;
import model.Usuario;

@ManagedBean
public class UsuarioController {
	
	Usuario usuario = new Usuario();
	
	public void fazerLogin() 
	{
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuarioLogado = dao.verificarLogin(usuario.getMatricula(), usuario.getSenha());
		
		if (usuarioLogado == null)
			System.out.println("Usuario ou login incorretos");
		else
			System.out.println("login certo");
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}