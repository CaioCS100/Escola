package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;

import connection.ConnectionFactory;
import model.Pessoa;
import static constantes.queries.QuerriesFuncionario.*;
import static util.Mensagem.*;

public class FuncionarioDAO {
	
	public Pessoa procurarFuncionario(Long id)
	{
		Pessoa funcionario = null;
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(QUERY_CONSULTAR_FUNCIONARIO)) {
			pst.setLong(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next())
				{
					funcionario = new Pessoa();
					funcionario.setId(rs.getLong("id"));
					funcionario.setNome(rs.getString("nome"));
					funcionario.setDataNascimento(rs.getDate("data_nascimento"));
					funcionario.setCep(rs.getString("cep"));
					funcionario.setEndereco(rs.getString("endereco"));
					funcionario.setComplemento(rs.getString("complemento"));
					funcionario.setBairro(rs.getString("bairro"));
					funcionario.setCidade(rs.getString("cidade"));
					funcionario.setUf(rs.getString("uf"));
					funcionario.setMatricula_id(rs.getInt("id_matricula"));
				}
			}
		} catch (SQLException ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro em carregar o funcionário!");
		}
		
		return funcionario;
	}

}
