package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.ChatRoom;
import com.befree.data.model.ChatRoomVO;
import com.befree.data.model.Match;
import com.befree.data.model.User;
import com.befree.data.model.vo.MatchVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class MatchConverter {



    public MatchVO convertEntityToVO(Match match){
        MatchVO matchVO = new MatchVO();
        matchVO.setId(match.getId());
        matchVO.setMatchRoomVO(DozerConverter.parseObject(match.getMatchRoom(), ChatRoomVO.class));
        matchVO.setHisHerId(match.getHisHerId());
        matchVO.setYou(DozerConverter.parseObject(match.getYou(), UserVO.class));
        return matchVO;
    }


    public Match convertVoToEntity(MatchVO vo){
        Match entity = new Match();
        entity.setId(vo.getId());
        entity.setYou(DozerConverter.parseObject(vo.getYou(), User.class));
        entity.setHisHerId(vo.getHisHerId());
        entity.setMatchRoom(DozerConverter.parseObject(vo.getMatchRoomVO(), ChatRoom.class));
        return entity;
    }
}
