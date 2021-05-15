package com.befree.repository;

import com.befree.data.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    @Query("Select i from Image i where i.name =:imageName")
    Optional<Image> findByName(@Param("imageName") String imageName);
}
