package com.befree.repository;

import com.befree.data.model.Match;
import com.befree.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, String> {
    Optional<Match> findMatchByYourIdAndAndYourMatchId(String yourId, String yourMatchId);
}
