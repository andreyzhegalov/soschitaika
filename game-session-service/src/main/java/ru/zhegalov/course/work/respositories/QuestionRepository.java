package ru.zhegalov.course.work.respositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.zhegalov.course.work.model.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    List<Question> findBySessionId(String sessionId);

    List<Question> findBySessionIdAndAnswerIsNotNull(String sessionId);

}
