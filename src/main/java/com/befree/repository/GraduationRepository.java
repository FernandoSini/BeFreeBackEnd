package com.befree.repository;

import com.befree.data.model.Graduation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GraduationRepository extends JpaRepository<Graduation,Integer> {


    @Query("Select g from Graduation g where g.courseName =:name")
    Graduation findByName(String name);
}
