package br.com.si5.evolution.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Naruto
 */
public class RelatorioFactory {
    
    private static Connection getConexao() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/evolution", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conexao;
    }

    public static void RelatorioAvaliacao(String relatorio, Integer idavalicao) {
        FacesContext context = FacesContext.getCurrentInstance(); 
        try {
            HttpServletResponse response = (HttpServletResponse) context
                    .getExternalContext().getResponse();
            InputStream reportStream = context.getExternalContext().getResourceAsStream(relatorio);
            response.setContentType("application/pdf");
            try (ServletOutputStream servletOutputStream = response.getOutputStream()) {
                Map<String, Object> map = new HashMap<>();
                map.put("IDAVALIACAO", idavalicao);
                JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, map, getConexao());
                servletOutputStream.flush();
            }
        } catch (JRException | IOException e) {
            e.printStackTrace();
        } finally {
            context.responseComplete();
        }
    }
}