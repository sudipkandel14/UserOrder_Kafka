package com.verification.demo.dao;

import com.verification.demo.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {
}
