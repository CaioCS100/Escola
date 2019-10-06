package model;

public class Usuario {
	
	private Long id;
	private Integer matricula;
	private String email;
	private String senha;
	private Integer categoria;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getMatricula() {
		return matricula;
	}
	
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Integer getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
}