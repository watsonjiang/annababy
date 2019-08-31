package com.watson.annababy.service.aa;

import com.watson.annababy.model.aa.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByName(String name);
}
