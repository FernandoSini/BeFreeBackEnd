package com.befree.repository;

import com.befree.data.model.Graduation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GraduationRepository extends JpaRepository<Graduation,Integer> {


    @Query("Select g from Graduation g where g.courseName =:name")
    Graduation findByName(String name);

    //buscando as graduacoes do usuario
    @Query("Select g from Graduation g join g.users u where u.userName =:userName")
    List<Graduation> findGraduationByUserName(String userName);
}
