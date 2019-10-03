package constantes.queries;

public class QuerriesFuncionario {
	public static final String QUERY_CONSULTAR_FUNCIONARIO = 
			"select f.id, nome, data_nascimento, cep, endereco, complemento, bairro, cidade, uf, id_matricula"
			+ " from funcionarios as f join dados_matricula as mat on f.id_matricula = mat.id"
			+ " where id_matricula = ?";
}
