package com.github.dchristofolli.dropbox.v1.user.repository;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ObjectUtils;

import java.util.List;

@AllArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Override
    public List<UserModel> find(UserModel user) {
        Criteria criteria = Criteria.where("inactivationDate").exists(false);
        criteriaById(user.getId(), criteria);
        criteriaByCpf(user.getCpf(), criteria);
        criteriaByUserName(user.getName(), criteria);
        criteriaByEmail(user.getEmail(), criteria);

        return mongoTemplate.find(Query.query(criteria), UserModel.class);
    }

    private void criteriaByEmail(String email, Criteria criteria) {
        if (!ObjectUtils.isEmpty(email))
            criteria.and("email").regex(email);
    }

    private void criteriaByUserName(String username, Criteria criteria) {
        if (!ObjectUtils.isEmpty(username))
            criteria.and("username").regex(username);
    }

    private void criteriaByCpf(String cpf, Criteria criteria) {
        if (!ObjectUtils.isEmpty(cpf))
            criteria.and("cpf").regex(cpf);
    }

    private void criteriaById(String id, Criteria criteria) {
        if (!ObjectUtils.isEmpty(id))
            criteria.and("id").regex(id);
    }
}
