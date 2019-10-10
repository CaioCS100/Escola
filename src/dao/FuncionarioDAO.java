package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;

import connection.ConnectionFactory;
import model.Endereco;
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
					funcionario.setEmail(rs.getString("email"));
					funcionario.getEndereco().setCep(rs.getString("cep"));
					funcionario.getEndereco().setLogradouro(rs.getString("endereco"));
					funcionario.getEndereco().setComplemento(rs.getString("complemento"));
					funcionario.getEndereco().setBairro(rs.getString("bairro"));
					funcionario.getEndereco().setCidade(rs.getString("cidade"));
					funcionario.getEndereco().setUf(rs.getString("uf"));
					funcionario.getEndereco().setNumero(rs.getString("numero"));
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
			pstMatricula.setString(2, funcionario.getSenha());
			pstMatricula.setInt(3, funcionario.getCategoria());
			
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
				pstFuncionario.setString(5, funcionario.getEmail());
				pstFuncionario.setString(6, funcionario.getEndereco().getCep());
				pstFuncionario.setString(7, funcionario.getEndereco().getLogradouro());
				pstFuncionario.setString(8, funcionario.getEndereco().getComplemento());
				pstFuncionario.setString(9, funcionario.getEndereco().getBairro());
				pstFuncionario.setString(10, funcionario.getEndereco().getCidade());
				pstFuncionario.setString(11, funcionario.getEndereco().getUf());
				pstFuncionario.setString(12, funcionario.getEndereco().getNumero());
				pstFuncionario.setInt(13, matriculaID);
				pstFuncionario.executeUpdate();
			} 
			
			conn.commit();
			return true;
		} catch (Exception ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro em cadastrar o Funcionário!");
		}
		
		return false;
	}
	
	public Boolean verificarSeExisteCpfCadastrado(String cpf)
	{
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(QUERY_CONSULTAR_CPF)) {
			pst.setString(1, cpf);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next())
					return true;
					
			}
		} catch (SQLException ex) {
			criarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro em verificar cpf do funcionário!");
		}
		
		return false;
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
}