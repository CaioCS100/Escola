package service;

import java.io.IOException;
import java.util.Date;

public interface RelatorioService {

    void gerarRelatorioFuncionario(Date dataNascimento) throws IOException;
}
