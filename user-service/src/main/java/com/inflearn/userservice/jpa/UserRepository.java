package com.inflearn.userservice.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
//    save, find__
    UserEntity findAllByUserId(String userId);
    UserEntity findByEmail(String email);

}
