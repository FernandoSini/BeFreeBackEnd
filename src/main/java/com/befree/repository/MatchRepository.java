package com.befree.repository;

import com.befree.data.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, String> {

    @Query("Select m from Match m where m.you.id =:yourId and m.hisHer.id =:hisHerId")
     Optional<Match> findMatchByYourIdAndAndYourMatchId(@Param("yourId") String yourId, @Param("hisHerId")String hisHerId);

    @Query("Select m from Match m where m.you.id =:yourId")
    Optional<List<Match>>findAllYourMatchesByYourId(@Param("yourId") String yourId);

    @Query("Select m from Match  m  where m.you.id =:yourId and m.hisHer.id =:herHisId")
    Optional<Match> findMatchByHisHerIdAndYouId(@Param("yourId") String yourId, @Param("herHisId") String herHisId);

}
