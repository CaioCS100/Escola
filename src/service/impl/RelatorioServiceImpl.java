package service.impl;

import connection.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import service.RelatorioService;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static shared.Constantes.CAMINHO_GERAL_RELATORIO;
import static shared.Constantes.CONTENT_TYPE_PDF;
import static shared.ParametrosRelatorios.FUNCIONARIO.PARAMETRO_DATA_NASCIMENTO;

public class RelatorioServiceImpl implements RelatorioService {

    @Override
    public void gerarRelatorioFuncionario(Date dataNascimento) throws IOException {
        String caminho = CAMINHO_GERAL_RELATORIO + "funcionario/";
        String relatorio = caminho + "funcionarios.jasper";

//        executarReport(relatorio, obterParametrosRelatorioFuncionario(dataNascimento), "funcionarios.pdf");
        executarReportEmNovaAba(relatorio, "funcionarios.pdf", obterParametrosRelatorioFuncionario(dataNascimento));
    }

    private Map<String, Object> obterParametrosRelatorioFuncionario(Date dataNascimento) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(PARAMETRO_DATA_NASCIMENTO, dataNascimento);

        return parametros;
    }

    private void executarReport(String relatorio, Map<String, Object> parametros, String nomeArquivo) throws IOException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            InputStream reportStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(relatorio);
            response.setContentType(CONTENT_TYPE_PDF);
            response.setHeader("Content-disposition", "attachment;filename=" + nomeArquivo);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(reportStream, response.getOutputStream(), parametros, connection);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void executarReportEmNovaAba(String caminhoRelatorio, String nomeRelatorio, Map parametros) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            InputStream relatorioStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(caminhoRelatorio);
            JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, connection);
            Exporter exporter = new JRPdfExporter();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.setExporterInput(new SimpleExporterInput(print));

            response.setContentType(CONTENT_TYPE_PDF);
            response.setHeader("Content-disposition", "inline; filename=" + nomeRelatorio + ".pdf");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            exporter.exportReport();
            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException | JRException | IOException e) {
            e.printStackTrace();
        }
    }
}
