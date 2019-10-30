package model;

import java.util.Date;

public class Pessoa extends Usuario {
	
	private String nome;
	private Date dataNascimento;
	private String cpf;
	private String telefone;
	private String email;
	private Endereco endereco;
	private Integer matricula_id;
	
	public Pessoa() {
		endereco = new Endereco();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Integer getMatricula_id() {
		return matricula_id;
	}
	
	public void setMatricula_id(Integer matricula_id) {
		this.matricula_id = matricula_id;
	}
}