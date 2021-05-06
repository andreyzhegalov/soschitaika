package zhegalov.course.work.service;

import zhegalov.course.work.model.Question;

public class QuestionGeneratorServiceImpl implements QuestionGeneratorService {

    @Override
    public Question create() {
        return new Question();
    }
}
