package com.befree.repository;

import com.befree.data.model.User;
import com.befree.data.model.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findUserByUserName(String userName);


    User findOneUserByUserName(String userName);

    Optional<User> findUserById(String id);
}
