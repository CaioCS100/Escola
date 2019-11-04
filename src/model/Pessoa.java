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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((matricula_id == null) ? 0 : matricula_id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Pessoa other = (Pessoa) obj;
		
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} 
		else if (!cpf.equals(other.cpf))
			return false;
		
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} 
		else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		
		if (email == null) {
			if (other.email != null)
				return false;
		} 
		else if (!email.equals(other.email))
			return false;
		
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} 
		else if (!endereco.equals(other.endereco))
			return false;
		
		if (matricula_id == null) {
			if (other.matricula_id != null)
				return false;
		} 
		else if (!matricula_id.equals(other.matricula_id))
			return false;
		
		if (nome == null) {
			if (other.nome != null)
				return false;
		} 
		else if (!nome.equals(other.nome))
			return false;
		
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		}
		else if (!telefone.equals(other.telefone))
			return false;
		
		return true;
	}
	
	
}