package com.befree.controllers;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.vo.MatchVO;
import com.befree.services.MatchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchServices matchServices;

    @GetMapping("/{yourId}")
    public ResponseEntity<List<MatchVO>> getAllYourMatches(@PathVariable("yourId") String yourId){
            var vo = matchServices.getYourMatchesByYourId(yourId);
            return ResponseEntity.ok(vo);
    }

    @GetMapping("/{id}/{senderId}")
    public ResponseEntity<MatchVO> getMatches(@PathVariable("id") String id, @PathVariable("senderId")String senderId){
            var vo = matchServices.getMatchToChatRoom(id,senderId);
            return ResponseEntity.ok(vo);
    }
}
