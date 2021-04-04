package com.befree.repository;

import com.befree.data.model.Gender;
import com.befree.data.model.User;
import com.befree.data.model.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("Select u from User u where u.email =:email")
    Optional<Boolean> findUserByEmail(String email);

    Optional<User>getUserByUserName(String userName);

    //ESSE é usado na verificação de cadastro
    Optional<User> findOneUserByUserName(String userName);

    //esse é usado para verificar o login e é usado no metodo que contém o userDetails
    @Query("Select u from User u WHERE u.userName =:username")
    Optional<User> findByUsername(String username);

    @Query("Select u from User u where u.gender <>:yourGender")
    Optional<List<User>> findUserByGenders(@Param("yourGender") Gender yourGender);

    @Query("Select u from User u where u.gender =:genderToSearch")
    Optional<List<User>> findUsersByOneGender(@Param("genderToSearch") Gender genderToSearch);


    Optional<User> findUserById(String id);
}
