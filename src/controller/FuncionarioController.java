package controller;

import javax.faces.bean.ManagedBean;

import model.Pessoa;
import static util.Sessao.*;

@ManagedBean
public class FuncionarioController {

	private Pessoa funcionario = new Pessoa();
	private Pessoa funcionarioSelecionado;
	private final Pessoa funcionarioSessao;
	private static final String FUNCIONARIO_SESSAO = "funcionario";

	public FuncionarioController() {
		this.funcionarioSessao = (Pessoa) recuperarObjetoDaSessao(FUNCIONARIO_SESSAO);
	}

	public void cadastrarFuncionario()
	{
		System.out.println("chegou aqui");
	}
	
	
	
	
	
	
	

	public Pessoa getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Pessoa funcionario) {
		this.funcionario = funcionario;
	}

	public Pessoa getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Pessoa funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public Pessoa getFuncionarioSessao() {
		return funcionarioSessao;
	}
}