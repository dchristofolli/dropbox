package com.github.dchristofolli.dropbox.repositories;

import com.github.dchristofolli.dropbox.models.UserInput;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserInput, String> {

}
