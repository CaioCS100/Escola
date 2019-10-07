package constantes.queries;

public class QueriesLogin {
	
	public static final String QUERY_CONSULTAR_LOGIN = 
			"select id, matricula, email, id_categoria"
			+ " from dados_matricula"
			+ " where matricula = ? and senha = ? and ativo = true";
	
	public static final String QUERY_CADASTRAR_DADOS_MATRICULA =
			"insert into dados_matricula(matricula, email, senha, id_categoria)"
			+ " values (?, ?, ?, ?)"
			+ " returning id";
	
	public static final String QUERY_CONSULTAR_EMAIL = 
			"select email"
			+ " from dados_matricula"
			+ " where email like ?";
	
	public static final String QUERY_CONSULTAR_MATRICULA = 
			"select matricula"
			+ " from dados_matricula"
			+ " where matricula = ?";
}
