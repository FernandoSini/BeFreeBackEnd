package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.ChatRoom;
import com.befree.data.model.ChatRoomVO;
import com.befree.data.model.Match;
import com.befree.data.model.vo.MatchVO;
import com.befree.data.model.vo.UserVO;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchServices {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ChatRoomServices chatRoomServices;


    public MatchVO saveMatch(MatchVO matchVO) {
        var entity = DozerConverter.parseObject(matchVO, Match.class);
        var vo = DozerConverter.parseObject((matchRepository.saveAndFlush(entity)), MatchVO.class);
        return vo;
    }

//    public MatchVO getMatch(UserVO yourData, UserVO heOrHerData, boolean createIfNotExists) {
//        return matchRepository.findMatchByYourIdAndAndYourMatchId(
//                yourData.getId(), heOrHerData.getId()).map(Match::getId)
//                .or(() -> {
//                            if (!createIfNotExists) {
//                                throw new ResourceNotFoundException("This match already exists");
//                            }
//                            var matchId = String.format("%s_%s", yourData.getId(),
//                                    heOrHerData.getId());
//                            ChatRoom chatRoom = ChatRoom.builder().chatId(
//                                    String.valueOf(chatRoomServices.getChatId(yourData.getId(), heOrHerData.getId(), true)))
//                                    .build();
//                            MatchVO matchByYou = MatchVO.builder()
//                                    .id(matchId)
//                                    .yourId(yourData)
//                                    .yourMatchId(heOrHerData.getId())
//                                    .matchRoom(chatRoom).
//                                            build();
//                            MatchVO hisHerMatch = MatchVO.builder()
//                                    .id(matchId)
//                                    .yourId(heOrHerData)
//                                    .yourMatchId(yourData.getId())
//                                    .matchRoom(chatRoom)
//                                    .build();
//                            yourData.addMatch(matchByYou);
//                            heOrHerData.addMatch(hisHerMatch);
//                            saveMatch(matchByYou);
//                            saveMatch(hisHerMatch);
//
//                            return matchByYou;
//                        }
//                ).orElseThrow(()-> new ResourceNotFoundException("Match Not Found"));
//    }

    //é a mesma coisa do decima só que difere que usa os Objetos users
    public MatchVO setMatch(UserVO yourData, UserVO hisOrHerData) {
        var matchData = matchRepository
                .findMatchByYourIdAndAndYourMatchId(
                        yourData.getId(), hisOrHerData.getId());

        // if (matchData.isPresent() || !matchData.isEmpty() || matchData != null) {
        //     return DozerConverter.parseObject(matchData, MatchVO.class);
        // }
       if(!matchData.isPresent() || matchData.isEmpty() || matchData == null) {
            var matchId = String.format("%s_%s", yourData.getId(),
                    hisOrHerData.getId());
            ChatRoom chatRoom = ChatRoom.builder().chatId(
                    String.valueOf(chatRoomServices.getChatId(yourData.getId(), hisOrHerData.getId(), true)))
                    .build();
            var chatRoomVO = DozerConverter.parseObject(chatRoom, ChatRoomVO.class);
            MatchVO matchByYou = MatchVO.builder()
                    .id(matchId)
                    .you(yourData)
                    .hisHerId(hisOrHerData.getId())
                    .chatRoomVO(chatRoomVO).
                            build();
            MatchVO hisHerMatch = MatchVO.builder()
                    .id(matchId)
                    .you(hisOrHerData)
                    .hisHerId(yourData.getId())
                    .chatRoomVO(chatRoomVO)
                    .build();
            yourData.addMatch(matchByYou);
            hisOrHerData.addMatch(hisHerMatch);
            saveMatch(matchByYou);
            saveMatch(hisHerMatch);

            return matchByYou;
        }else if(matchData.isPresent() || !matchData.isEmpty() || matchData == null){
            return DozerConverter.parseObject(matchData, MatchVO.class);
        }else{
            throw new ResourceNotFoundException("Not Found Match");
        }
    }


}
