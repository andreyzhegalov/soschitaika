package zhegalov.course.work.service;

import zhegalov.course.work.dto.AnswerDto;
import zhegalov.course.work.model.Question;

public interface AnswerService {

    Question saveNewAnswer(AnswerDto answerDto);

}

