package zhegalov.course.work.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

@Configuration
public class ApplicationConfig {
    @Bean
    JasperReport getJasperReport(@Value("${jasper.template}") String reportTemplatePath) throws JRException {
        final var reportTemplate = getClass().getClassLoader().getResourceAsStream(reportTemplatePath);
        return JasperCompileManager.compileReport(reportTemplate);
    }
}
