package com.github.dchristofolli.dropbox.repositories;
import com.github.dchristofolli.dropbox.controllers.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
