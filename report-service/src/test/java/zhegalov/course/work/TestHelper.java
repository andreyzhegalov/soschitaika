package zhegalov.course.work;

import java.util.List;

import zhegalov.course.work.dto.ReportItemDto;

public class TestHelper {

    public static List<ReportItemDto> createReportItemList(){
        final var item1 = new ReportItemDto();
        item1.setAnswer("answer1");
        item1.setQuestion("question1");
        item1.setCorrectAnswer("answer1");
        item1.setCorrect(true);

        final var item2 = new ReportItemDto();
        item2.setAnswer("answer2");
        item2.setQuestion("question2");
        item2.setCorrectAnswer("answer1");
        item2.setCorrect(false);
        return List.of(item1, item2);
    }

}

