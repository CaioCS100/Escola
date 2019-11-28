package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import dao.FuncionarioDAO;
import dao.UsuarioDAO;
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
	private final UsuarioDAO usuarioDAO;
	private final ArrayList<String> ufs;
	private ArrayList<Pessoa> listaFuncionarios;
	private boolean edicaoFuncionario;
	private boolean leituraFuncionario;
	private String senhaAtual;
	private String novaSenha;

	public FuncionarioController() {
		this.funcionarioSessao = (Pessoa) recuperarObjetoDaSessao(FUNCIONARIO_SESSAO);
		this.ufs = CARREGAR_UFS;
		this.funcionario = new Pessoa();
		this.dao = new FuncionarioDAO();
		this.usuarioDAO = new UsuarioDAO();
	}

	public void cadastrarFuncionario()
	{		
		this.funcionario.setCategoria(FUNCIONARIO.getValor());
		if (!verificarSeExisteCPFCadastrado(this.funcionario.getCpf(), this.funcionario.getId()) &&
				!verificarSeExisteEmailCadastrado(this.funcionario.getEmail(), this.funcionario.getId()))
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
	
	public void editarFuncionario()
	{
		if (!verificarSeExisteCPFCadastrado(this.funcionarioSelecionado.getCpf(), this.funcionarioSelecionado.getId()) &&
				!verificarSeExisteEmailCadastrado(this.funcionarioSelecionado.getEmail(), this.funcionarioSelecionado.getId()))
		{
			if (this.dao.alterarFuncionario(funcionarioSelecionado))
			{
				criarMensagem(FacesMessage.SEVERITY_INFO, "Funcionário editado com sucesso!", "sucesso!");
				fecharModalEdicaoFuncionario();
				carregarTabelaFuncionarios();
				PrimeFaces.current().ajax().update(":formTabela");
			}
			else
				criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro em editar o funcionário", "erro!");
		}
	}
	
	public void editarSenha()
	{
		if (verificarSeExisteSenha(this.senhaAtual, funcionario.getId()) && !verificarSeSenhaSaoIguais(this.senhaAtual, this.novaSenha)) {
			if (this.usuarioDAO.alterarSenha(this.novaSenha, funcionario.getId())) {
				criarMensagem(FacesMessage.SEVERITY_INFO, "Senha alterada com sucesso!", "sucesso!");
				fecharModalEdicaoSenha();
			}
			else
				criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro em alterar a senha!", "erro!");
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
	
	public void abrirModalEditarSenha()
	{
		PrimeFaces.current().ajax().update(":formAlterarSenha");
		PrimeFaces.current().executeScript("PF('dlgAlterarSenha').show()");
	}
	
	public void verificarCEP()
	{
		try {
			if (!this.edicaoFuncionario)
				this.funcionario.setEndereco(consulta(this.funcionario.getEndereco().getCep().replaceAll("\\D", "")));
			else
				this.funcionarioSelecionado.setEndereco(consulta(this.funcionarioSelecionado.getEndereco().getCep().replaceAll("\\D", "")));
			
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
	
	public void fecharModalEdicaoSenha() 
	{
		PrimeFaces.current().executeScript("PF('dlgAlterarSenha').hide()");
	}
	
	private void buscarFuncionario()
	{
		this.funcionarioSelecionado = this.dao.procurarFuncionario(Long.valueOf((funcionario.getMatricula_id())));
		PrimeFaces.current().ajax().update(":formEditarFuncionario");
		PrimeFaces.current().executeScript("PF('dlgEditarFuncionario').show()");
	}
	
	private Boolean verificarSeExisteSenha(String senhaAtual, Long id)
	{
		if (!usuarioDAO.verificarSeExisteSenha(senhaAtual, id)) {
			criarMensagem(FacesMessage.SEVERITY_WARN, "O campo senha atual está incompatível com o valor no banco de daods!", "Erro!");
			return false;
		}
		
		return true;	
	}
	
	private Boolean verificarSeSenhaSaoIguais(String senhaAtual, String novaSenha)
	{
		if (senhaAtual.equals(novaSenha)) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro em alterar a senha. A senha antiga e a senha atual são as mesmas", "erro!");
			return true;
		}
		
		return false;
	}
	
	private Boolean verificarSeExisteEmailCadastrado(String email, Long id) 
	{
		 if (this.dao.verificarSeExisteEmailCadastrado(email, id))
			 criarMensagem(FacesMessage.SEVERITY_WARN, "Email já cadastrado!", "Email já cadastrado!");
		 else
			 return false;
		
		return true;
	}
	
	private Boolean verificarSeExisteCPFCadastrado(String cpf, Long id)
	{
		if (this.dao.verificarSeExisteCpfCadastrado(cpf, id)) 
			criarMensagem(FacesMessage.SEVERITY_WARN, "CPF já cadastrado!", "CPF já cadastrado!");
		else
			return false;
		
		return true;
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

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
}