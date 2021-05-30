package ru.zhegalov.course.work.service;

import ru.zhegalov.course.work.model.Question;
import ru.zhegalov.course.work.dto.AnswerDto;

public interface AnswerService {

    Question saveNewAnswer(AnswerDto answerDto);

}

