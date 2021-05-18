package zhegalov.course.work;

import java.util.List;

import zhegalov.course.work.controllers.dto.ReportItemDto;

public class TestHelper {

    public static List<ReportItemDto> createReportItemList(){
        final var item1 = new ReportItemDto();
        item1.setAnswer("answer1");
        item1.setQuestion("question1");
        item1.setCorrectAnswer("answer1");
        item1.setCorrect(true);
        return List.of(item1);
    }

}

