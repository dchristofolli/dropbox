package com.github.dchristofolli.user.repository;


import com.github.dchristofolli.user.model.UserInput;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserInput, String> {

}
