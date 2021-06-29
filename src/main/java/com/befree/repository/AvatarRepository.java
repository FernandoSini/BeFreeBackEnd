package com.befree.repository;

import com.befree.data.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, String> {
    @Query("Select a from Avatar a where a.name =:imageName")
    Optional<Avatar> findByName(@Param("imageName") String imageName);
}
