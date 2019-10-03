package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;

import connection.ConnectionFactory;
import static constantes.queries.QueriesLogin.*;
import model.Usuario;
import static util.Mensagem.*;

public class UsuarioDAO {
	
	public Usuario verificarLogin(Integer matricula, String senha)
	{
		Usuario usuario = null;
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(QUERY_CONSULTAR_LOGIN)) {
			pst.setInt(1, matricula);
			pst.setString(2, senha);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next())
				{
					usuario = new Usuario();
					usuario.setId(rs.getLong("id"));
					usuario.setMatricula(rs.getInt("matricula"));
					usuario.setCategoria(rs.getInt("id_categoria"));
				}
			}
		} catch (SQLException ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro em consultar os dados do login com o banco de dados!");
	        ex.printStackTrace();
		}
		
		return usuario;
	}

}
