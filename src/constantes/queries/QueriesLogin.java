package constantes.queries;

public class QueriesLogin {
	
	public static final String QUERY_CONSULTAR_LOGIN = 
			"select id, matricula, id_categoria"
			+ " from dados_matricula"
			+ " where matricula = ? and senha = ? and ativo = true";
	
	public static final String QUERY_CADASTRAR_DADOS_MATRICULA =
			"insert into dados_matricula(matricula, senha, id_categoria)"
			+ " values (?, ?, ?)"
			+ " returning id";
	
	public static final String QUERY_CONSULTAR_MATRICULA = 
			"select matricula"
			+ " from dados_matricula"
			+ " where matricula = ?";
}
