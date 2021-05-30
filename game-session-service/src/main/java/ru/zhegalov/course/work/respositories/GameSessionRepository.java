package ru.zhegalov.course.work.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.zhegalov.course.work.model.GameSession;

@Repository
public interface GameSessionRepository extends MongoRepository<GameSession, String>{

}

