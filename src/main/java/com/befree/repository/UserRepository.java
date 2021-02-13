package com.befree.repository;

import com.befree.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findUserByUserName(String userName);

    Optional<User> findOneUserByUserName(String userName);

    User findUserById(String id);
}
