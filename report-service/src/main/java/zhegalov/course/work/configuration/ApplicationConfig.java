package zhegalov.course.work.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@Configuration
public class ApplicationConfig {

    @SuppressWarnings("rawtypes")
    // @Bean
    Exporter getPdfExporter(ByteArrayWrapper byteArrayWrapper) {
        final var exporter = new JRPdfExporter();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayWrapper.getOutputStream()));

        final var reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        final var exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        return exporter;
    }

    @SuppressWarnings("rawtypes")
    @Bean
    Exporter getHtmlExporter(ByteArrayWrapper byteArrayWrapper) {
        final var exporter = new HtmlExporter();
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(byteArrayWrapper.getOutputStream()));
        return exporter;
    }

    @Bean
    ByteArrayWrapper getByteArrayWrapper() {
        return new ByteArrayWrapper();
    }
}
