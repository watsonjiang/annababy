package com.watson.annababy.service.aa;

import com.watson.annababy.model.aa.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByName(String name);

    @Query("select r.users from RoleEntity r where r.id = ?1")
    List<UserEntity> findByRoleId(Long roleId);
}
