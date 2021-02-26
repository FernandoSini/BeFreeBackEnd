package com.befree.repository;

import com.befree.data.model.User;
import com.befree.data.model.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {



    Optional<User>getUserByUserName(String userName);

    //ESSE é usado na verificação de cadastro
    Optional<User> findOneUserByUserName(String userName);

    Optional<User> findUserById(String id);
}
