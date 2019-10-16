package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeFacesContext;

import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import dao.FuncionarioDAO;
import model.Pessoa;
import util.ConsultarCep;

import static enums.Categoria.*;
import static util.Sessao.*;
import static util.Ufs.*;
import static util.ConsultarCep.*;

import java.util.ArrayList;

import static util.Mensagem.*;

@ManagedBean
@ViewScoped
public class FuncionarioController {

	private Pessoa funcionario;
	private final Pessoa funcionarioSessao;
	private static final String FUNCIONARIO_SESSAO = "funcionario";
	private final FuncionarioDAO dao;
	private final ArrayList<String> ufs;
	private ArrayList<Pessoa> listaFuncionarios;
	private Boolean edicaoFuncionario;
	private Boolean cadastroFuncionario;
	private Boolean somenteLeitura;

	public FuncionarioController() {
		this.funcionarioSessao = (Pessoa) recuperarObjetoDaSessao(FUNCIONARIO_SESSAO);
		this.ufs = CARREGAR_UFS;
		this.funcionario = new Pessoa();
		this.dao = new FuncionarioDAO();
		this.cadastroFuncionario = true;
	}

	public void cadastrarFuncionario()
	{		
		this.funcionario.setCategoria(FUNCIONARIO.getValor());
		if (this.dao.cadastrarFuncionario(funcionario))
		{
			criarMensagem(FacesMessage.SEVERITY_INFO, "Funcionário cadastrado com sucesso!", "sucesso!");
			this.funcionario = new Pessoa();
		}
		else
			criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro em cadastrar o funcionário", "erro!");
	}
	
	public void editarFuncionario()
	{
		System.out.println("aqui");
		if (this.somenteLeitura)
			this.somenteLeitura = false;
		else
			System.out.println("Pronto para editar");
	}
	
	public void verificarCEP()
	{
		try {
			this.funcionario.setEndereco(consulta(this.funcionario.getEndereco().getCep().replaceAll("\\D", "")));
		} catch (SQLException_Exception | SigepClienteException ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "erro!");
		}
			
	}
	
	public void carregarTabelaFuncionarios()
	{
		this.listaFuncionarios = this.dao.listarFuncionarios();
	}
	
	public void visualizarFuncionario()
	{
		this.funcionario = this.dao.procurarFuncionario(Long.valueOf(((Integer) funcionario.getMatricula_id())));
		this.edicaoFuncionario = true;
		this.cadastroFuncionario = false;
		this.somenteLeitura = true;
		PrimeFaces.current().ajax().update("@(.forms)");
		PrimeFaces.current().executeScript("PF('dlgFuncionario').show()");
	}

	public Pessoa getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Pessoa funcionario) {
		this.funcionario = funcionario;
	}

	public Pessoa getFuncionarioSessao() {
		return funcionarioSessao;
	}

	public ArrayList<String> getUfs() {
		return ufs;
	}

	public ArrayList<Pessoa> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public Boolean getEdicaoFuncionario() {
		return edicaoFuncionario;
	}

	public Boolean getCadastroFuncionario() {
		return cadastroFuncionario;
	}

	public Boolean getSomenteLeitura() {
		return somenteLeitura;
	}
}