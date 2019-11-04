package constantes.queries;

public class QuerriesFuncionario {
	public static final String QUERY_CONSULTAR_FUNCIONARIO = 
			"select func.id, nome, data_nascimento, cpf, telefone, email, cep, endereco, complemento, bairro,"
			+ " cidade, uf, numero, id_matricula, matricula"
			+ " from funcionarios as func join dados_matricula as mat on func.id_matricula = mat.id"
			+ " where id_matricula = ?";
	
	public static final String QUERY_CADASTRAR_FUNCIONARIO =
			"insert into funcionarios(nome, data_nascimento, cpf, telefone, email, cep, endereco, complemento, bairro,"
			+ " cidade, uf, numero, id_matricula)"
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String QUERY_ALTERAR_FUNCIONARIO = 
			"update funcionarios set nome = ?, data_nascimento = ?, cpf = ?, telefone = ?, email = ?, cep = ?,"
			+ " endereco = ?, cidade = ?, bairro = ?, uf = ?, numero = ?, complemento = ?"
			+ " where id = ?";
	
	public static final String QUERY_CONSULTAR_CPF = 
			"select cpf"
			+ " from funcionarios"
			+ " where cpf like ?";
	
	public static final String QUERY_CONSULTAR_EMAIL = 
			"select email"
			+ " from funcionarios"
			+ " where email like ?";
	
	public static final String QUERY_LISTAR_FUNCIONARIOS = 
			"select func.id, nome, data_nascimento, cpf, telefone, email, id_matricula"
			+ " from funcionarios as func join dados_matricula as mat on func.id_matricula = mat.id"
			+ " where mat.ativo = true";
	
	public static final String CONDICAO_FILTRO_POR_ID = 
			" and id != ?";
}
