package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.validator.ValidatorException;

import org.primefaces.PrimeFaces;

import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import dao.FuncionarioDAO;
import model.Pessoa;

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
	private Pessoa funcionarioSelecionado;
	private final Pessoa funcionarioSessao;
	private static final String FUNCIONARIO_SESSAO = "funcionario";
	private final FuncionarioDAO dao;
	private final ArrayList<String> ufs;
	private ArrayList<Pessoa> listaFuncionarios;
	private Boolean edicaoFuncionario;
	private Boolean leituraFuncionario;

	public FuncionarioController() {
		this.funcionarioSessao = (Pessoa) recuperarObjetoDaSessao(FUNCIONARIO_SESSAO);
		this.ufs = CARREGAR_UFS;
		this.funcionario = new Pessoa();
		this.dao = new FuncionarioDAO();
	}

	public void cadastrarFuncionario()
	{		
		this.funcionario.setCategoria(FUNCIONARIO.getValor());
		if (verificarSeExisteCPFCadastrado(this.funcionario.getCpf()) && verificarSeExisteEmailCadastrado(this.funcionario.getEmail()))
		{
			if (this.dao.cadastrarFuncionario(funcionario)) 
			{
				criarMensagem(FacesMessage.SEVERITY_INFO, "Funcionário cadastrado com sucesso!", "sucesso!");
				this.funcionario = new Pessoa();
				carregarTabelaFuncionarios();
				PrimeFaces.current().ajax().update(":formTabela");
			}
			else
				criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro em cadastrar o funcionário", "erro!");
		}
	}
	
	public void abrirModalCadastroFuncionario()
	{
		this.funcionario = new Pessoa();
		PrimeFaces.current().ajax().update(":formCadFuncionario");
		PrimeFaces.current().executeScript("PF('dlgCadFuncionario').show()");
	}
	
	public void abrirModalVisualizarFuncionario()
	{
		condicaoVisualizacaoEdicao(true, false);
		buscarFuncionario();
	}
	
	public void abrirModalEditarFuncionario()
	{
		condicaoVisualizacaoEdicao(false, true);
		buscarFuncionario();
	}
	
	public void buscarFuncionario()
	{
		this.funcionarioSelecionado = this.dao.procurarFuncionario(Long.valueOf((funcionario.getMatricula_id())));
		PrimeFaces.current().ajax().update(":formEditarFuncionario");
		PrimeFaces.current().executeScript("PF('dlgEditarFuncionario').show()");
	}
	
	public void editarFuncionario()
	{
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
	
	public void fecharModalCadastroFuncionario() 
	{
		PrimeFaces.current().executeScript("PF('dlgCadFuncionario').hide()");
	}
	
	public void fecharModalEdicaoFuncionario() 
	{
		PrimeFaces.current().executeScript("PF('dlgEditarFuncionario').hide()");
	}
	
	private Boolean verificarSeExisteEmailCadastrado(String email) 
	{
		 if (this.dao.verificarSeExisteEmailCadastrado(email))
			 criarMensagem(FacesMessage.SEVERITY_WARN, "Email já cadastrado!", "Email já cadastrado!");
		 else
			 return true;
		
		return false;
	}
	
	private Boolean verificarSeExisteCPFCadastrado(String cpf)
	{
		if (this.dao.verificarSeExisteCpfCadastrado(cpf))
			criarMensagem(FacesMessage.SEVERITY_WARN, "CPF já cadastrado!", "CPF já cadastrado!");
		else
			return true;
		
		return false;
	}
	
	private void condicaoVisualizacaoEdicao(Boolean visualizandoFuncionario, Boolean editandoFuncionario) {
		this.leituraFuncionario = visualizandoFuncionario;
		this.edicaoFuncionario = editandoFuncionario;
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

	public ArrayList<String> getUfs() {
		return ufs;
	}

	public ArrayList<Pessoa> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public Boolean getEdicaoFuncionario() {
		return edicaoFuncionario;
	}

	public Boolean getLeituraFuncionario() {
		return leituraFuncionario;
	}
}