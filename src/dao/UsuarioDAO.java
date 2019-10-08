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
					usuario.setEmail(rs.getString("email"));
					usuario.setCategoria(rs.getInt("id_categoria"));
				}
			}
		} catch (SQLException ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro em consultar os dados do login com o banco de dados!");
		}
		
		return usuario;
	}
	
	public Boolean verificarSeExisteEmailCadastrado(String email)
	{
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(QUERY_CONSULTAR_EMAIL)) {
			pst.setString(1, email);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next())
					return true;
			}
		} catch (SQLException ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro em verificar email do funcionário!");
		}
		
		return false;
	}
	
	public Boolean verificarSeExisteMatriculaCadastrada(Long matricula)
	{
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(QUERY_CONSULTAR_MATRICULA)) {
			pst.setLong(1, matricula);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next())
					return true;
			}
		} catch (SQLException ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro em verificar a matricula do funcionário!");
		}
		
		return false;
	}
}