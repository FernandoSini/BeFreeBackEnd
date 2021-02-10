package com.befree.repository;

import com.befree.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findUserByUserName(String userName);

    User findUserById(String id);
}
