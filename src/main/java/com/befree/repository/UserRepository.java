package com.befree.repository;

import com.befree.data.model.User;
import com.befree.data.model.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {



    Optional<User>getUserByUserName(String userName);

    //ESSE é usado na verificação de cadastro
    Optional<User> findOneUserByUserName(String userName);

    //esse é usado para verificar o login e é usado no metodo que contém o userDetails
    @Query("Select u from User u WHERE u.userName =:username")
    User findByUsername(String username);

    Optional<User> findUserById(String id);
}
