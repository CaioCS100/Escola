package constantes.queries;

public class QueriesLogin {
	
	public static final String QUERY_CONSULTAR_LOGIN = 
			"select id, matricula, id_categoria"
			+ " from dados_matricula"
			+ " where matricula = ? and senha = ? and ativo = true";
}
