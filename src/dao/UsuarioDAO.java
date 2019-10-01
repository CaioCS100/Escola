package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import connection.ConnectionFactory;
import static constantes.queries.QueriesUsuario.*;
import model.Usuario;

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
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, ex.getMessage(), "Erro");
	        FacesContext contexto = FacesContext.getCurrentInstance();
	        contexto.addMessage(null, msg);
	        ex.printStackTrace();
		}
		
		return usuario;
	}

}
