package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Match;
import com.befree.data.model.Message;
import com.befree.data.model.User;
import com.befree.data.model.vo.MatchVO;
import com.befree.data.model.vo.MessageVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class MatchConverter {



    public MatchVO convertEntityToVO(Match match){
        MatchVO matchVO = new MatchVO();
        matchVO.setId(match.getId());
        matchVO.setMessages(DozerConverter.parseListObjects(match.getMessages(), MessageVO.class));
        matchVO.setHisHer(DozerConverter.parseObject(match.getHisHer(), UserVO.class));
        matchVO.setYou(DozerConverter.parseObject(match.getYou(), UserVO.class));
        return matchVO;
    }


    public Match convertVoToEntity(MatchVO vo){
        Match entity = new Match();
        entity.setId(vo.getId());
        entity.setYou(DozerConverter.parseObject(vo.getYou(), User.class));
        entity.setHisHer(DozerConverter.parseObject(vo.getHisHer(), User.class));
        entity.setMessages(DozerConverter.parseListObjects(vo.getMessages(), Message.class));
        return entity;
    }
}
