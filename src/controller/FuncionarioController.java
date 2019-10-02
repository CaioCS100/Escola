package controller;

import javax.faces.bean.ManagedBean;

import model.Pessoa;
import static util.Sessao.*;

@ManagedBean
public class FuncionarioController {

	private Pessoa funcionario = new Pessoa();
	private static final String FUNCIONARIO_SESSAO = "funcionario";

	public FuncionarioController() {
		funcionario = (Pessoa) recuperarObjetoDaSessao(FUNCIONARIO_SESSAO);
	}

	public void deslogar() {
		removerObjetoDaSessao(FUNCIONARIO_SESSAO);
	}

	public Pessoa getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Pessoa funcionario) {
		this.funcionario = funcionario;
	}
}
