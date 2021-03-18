package com.befree.repository;

import com.befree.data.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, String> {

    @Query("Select m from Match m where m.you.id =:yourId and m.hisHerId =:hisHerId")
 Optional<Match> findMatchByYourIdAndAndYourMatchId(@Param("yourId") String yourId, @Param("hisHerId")String hisHerId);
}
