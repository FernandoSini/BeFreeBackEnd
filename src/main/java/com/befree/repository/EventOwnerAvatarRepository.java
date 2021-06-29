package com.befree.repository;

import com.befree.data.model.Avatar;
import com.befree.data.model.EventOwnerAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventOwnerAvatarRepository extends JpaRepository<EventOwnerAvatar, String> {
    @Query("Select a from EventOwnerAvatar a where a.name =:imageName")
    Optional<EventOwnerAvatar> findByName(@Param("imageName") String imageName);
}
