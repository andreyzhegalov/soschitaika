package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootTest
public class ReportServiceTest {

    @Configuration
    @Import(ReportServiceJasperReports.class)
    public static class TestContext{
    }

    @Autowired
    private ReportService reportService;

    @Test
    void shouldCreateReport(){
        assertThat( reportService.create() ).isNotNull();
    }

}

