package ru.zhegalov.course.work.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import ru.zhegalov.course.work.model.User;

@Repository
public class UserRepository {

    public Optional<User> findByName(String name){
        final User user = new User();
        user.setName(name);
        user.setPassword("{bcrypt}$2a$11$lbNOjdqSIknP3/KnllwDw.05TYcGOqxYzrEQvdfFQvRF9m9KLgE12"); //password
        user.setRole("USER");
        return Optional.of(user);
    }
}
