package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import zhegalov.course.work.controllers.dto.SessionDto;
import zhegalov.course.work.dto.ReportDto;

@Slf4j
@Service
public class SomeService {

    public ReportDto handler(SessionDto data) {
        log.debug("!!!!!!!!!!!!!! SomeService handler with {}", data);
        final var message = new ReportDto();
        message.setReportId(data.getSessionId() + " from service handler");
        sleep(1000);
        return message;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
