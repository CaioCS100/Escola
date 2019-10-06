package controller;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import model.Usuario;
import static util.Sessao.*;

import static util.Redirecionamento.*;
import static enums.Categoria.*;

import static util.Mensagem.*;

@ManagedBean
public class UsuarioController {
	
	private Usuario usuario = new Usuario();
	private static final String FUNCIONARIO_SESSAO = "funcionario";
	private static final String PROFESSOR_SESSAO = "professor";
	private static final String ALUNO_SESSAO = "cliente";
	
	public void fazerLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuarioLogado = dao.verificarLogin(usuario.getMatricula(), usuario.getSenha());
		
		if (usuarioLogado == null)
			criarMensagem(FacesMessage.SEVERITY_ERROR, "Login ou senha incorretos", "Erro de validação!");
		
		else if (usuarioLogado.getCategoria() == FUNCIONARIO.getValor())
			carregarFuncionario(usuarioLogado);
			
		else
			criarMensagem(FacesMessage.SEVERITY_INFO, "Login correto", "Sucesso!");
	}
	
	public void carregarFuncionario(Usuario usuario) {
		adicionarObjetoNaSessao(FUNCIONARIO_SESSAO, new FuncionarioDAO().procurarFuncionario(usuario.getId()));
		redirecionar("funcionario/principal");
	}
	
	public void sair() {
		redirecionar("login");
		removerObjetoDaSessao(FUNCIONARIO_SESSAO);
		removerObjetoDaSessao(PROFESSOR_SESSAO);
		removerObjetoDaSessao(ALUNO_SESSAO);
	}
	
	public void redirecionar(String caminhoDaPagina) {
		redirecionarParaPagina(caminhoDaPagina);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}