package constantes.queries;

public class QuerriesFuncionario {
	public static final String QUERY_CONSULTAR_FUNCIONARIO = 
			"select f.id, nome, data_nascimento, cpf, telefone, cep, endereco, complemento, bairro,"
			+ " cidade, uf, id_matricula"
			+ " from funcionarios as f join dados_matricula as mat on f.id_matricula = mat.id"
			+ " where id_matricula = ?";
	
	public static final String QUERY_CADASTRAR_FUNCIONARIO =
			"insert into funcionarios(nome, data_nascimento, cpf, telefone, cep, endereco, complemento, bairro,"
			+ " cidade, uf, id_matricula)"
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String QUERY_CONSULTAR_CPF = 
			"select cpf"
			+ " from funcionarios"
			+ " where cpf like ?";
}
