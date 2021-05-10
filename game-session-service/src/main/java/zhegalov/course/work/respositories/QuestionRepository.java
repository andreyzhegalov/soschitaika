package zhegalov.course.work.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import zhegalov.course.work.model.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

}
