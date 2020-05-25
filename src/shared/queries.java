package shared;

public interface queries {

    interface Login {
        String QUERY_CONSULTAR_LOGIN =
                "select id, matricula, id_categoria"
                        + " from dados_matricula"
                        + " where matricula = ? and senha = ? and ativo = true";

        String QUERY_CADASTRAR_DADOS_MATRICULA =
                "insert into dados_matricula(matricula, senha, id_categoria)"
                        + " values (?, ?, ?)"
                        + " returning id";

        String QUERY_CONSULTAR_MATRICULA =
                "select matricula"
                        + " from dados_matricula"
                        + " where matricula = ?";

        String QUERY_CONSULTAR_SENHA =
                "select senha"
                        + " from dados_matricula"
                        + " where id = (select id_matricula from funcionarios where id = ?) and senha = ?";

        String QUERY_ATUALIZAR_SENHA =
                "update dados_matricula"
                        + " set senha = ?, ultima_modificacao = now()"
                        + " where id = (select id_matricula from funcionarios where id = ?)";
    }

    interface Funcionario {
        String QUERY_CONSULTAR_FUNCIONARIO =
                "select func.id, nome, data_nascimento, cpf, telefone, email, cep, endereco, complemento, bairro,"
                        + " cidade, uf, numero, id_matricula, matricula"
                        + " from funcionarios as func join dados_matricula as mat on func.id_matricula = mat.id"
                        + " where id_matricula = ?";

        String QUERY_CADASTRAR_FUNCIONARIO =
                "insert into funcionarios(nome, data_nascimento, cpf, telefone, email, cep, endereco, complemento, bairro,"
                        + " cidade, uf, numero, id_matricula)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String QUERY_ALTERAR_FUNCIONARIO =
                "update funcionarios set nome = ?, data_nascimento = ?, cpf = ?, telefone = ?, email = ?, cep = ?,"
                        + " endereco = ?, cidade = ?, bairro = ?, uf = ?, numero = ?, complemento = ?"
                        + " where id = ?";

        String QUERY_CONSULTAR_CPF =
                "select cpf"
                        + " from funcionarios"
                        + " where cpf like ?";

        String QUERY_CONSULTAR_EMAIL =
                "select email"
                        + " from funcionarios"
                        + " where email like ?";

        String QUERY_LISTAR_FUNCIONARIOS =
                "select func.id, nome, data_nascimento, cpf, telefone, email, id_matricula"
                        + " from funcionarios as func join dados_matricula as mat on func.id_matricula = mat.id"
                        + " where mat.ativo = true";

        String CONDICAO_FILTRO_POR_ID =
                " and id != ?";
    }
}
