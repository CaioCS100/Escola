package shared.enums;

public enum Categoria {
	ALUNO(1),
	PROFESSOR(2),
	FUNCIONARIO(3);
	
	private final Integer valor;
	
	private Categoria(Integer valor) {
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}
	
}