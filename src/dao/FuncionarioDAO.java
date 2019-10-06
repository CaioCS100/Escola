package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;

import connection.ConnectionFactory;
import model.Pessoa;
import static constantes.queries.QuerriesFuncionario.*;
import static constantes.queries.QueriesLogin.QUERY_CADASTRAR_DADOS_MATRICULA;
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
					funcionario.setCpf(rs.getString("cpf"));
					funcionario.setTelefone(rs.getString("telefone"));
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
	
	public Boolean cadastrarFuncionario(Pessoa funcionario)
	{
		Integer matriculaID = 0;
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstMatricula = conn.prepareStatement(QUERY_CADASTRAR_DADOS_MATRICULA)) {
			pstMatricula.setInt(1, funcionario.getMatricula());
			pstMatricula.setString(2, funcionario.getEmail());
			pstMatricula.setString(3, funcionario.getSenha());
			pstMatricula.setInt(4, funcionario.getCategoria());
			
			try (ResultSet rs = pstMatricula.executeQuery()) {
				while (rs.next())
				{
					matriculaID = rs.getInt("id");
				}
			}
						
			try (PreparedStatement pstFuncionario = conn.prepareStatement(QUERY_CADASTRAR_FUNCIONARIO)) {
				pstFuncionario.setString(1, funcionario.getNome());
				pstFuncionario.setDate(2, new Date(funcionario.getDataNascimento().getTime()));
				pstFuncionario.setString(3, funcionario.getCpf());
				pstFuncionario.setString(4, funcionario.getTelefone());
				pstFuncionario.setString(5, funcionario.getCep());
				pstFuncionario.setString(6, funcionario.getEndereco());
				pstFuncionario.setString(7, funcionario.getComplemento());
				pstFuncionario.setString(8, funcionario.getBairro());
				pstFuncionario.setString(9, funcionario.getCidade());
				pstFuncionario.setString(10, funcionario.getUf());
				pstFuncionario.setInt(11, matriculaID);
				pstFuncionario.executeUpdate();
			} 
			
			conn.commit();
			return true;
		} catch (Exception ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro em cadastrar o Funcionário!");
		}
		
		return false;
	}

}
