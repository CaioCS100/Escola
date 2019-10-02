package constantes.queries;

public class QueriesUsuario {
	
	public static final String QUERY_CONSULTAR_LOGIN = 
			"select id, matricula, id_categoria"
			+ " from dados_matricula"
			+ " where matricula = ? and senha = ?";
}
