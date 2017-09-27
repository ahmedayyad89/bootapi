package com.project.mini.repository;

import com.project.mini.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findById(Integer id);
    Boolean existsByEmail(String email);
}
