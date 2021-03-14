package com.befree.services;

import com.befree.data.model.ChatRoom;
import com.befree.data.model.Match;
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


    public Match saveMatch(Match match) {
        matchRepository.saveAndFlush(match);
        return match;
    }

    public Optional<String> getMatch(String yourId, String heOrHerId, boolean createIfNotExists) {
        return matchRepository.findMatchByYourIdAndAndYourMatchId(
                yourId, heOrHerId).map(Match::getId)
                .or(() -> {
                            if (!createIfNotExists) {
                                return Optional.empty();
                            }
                            var matchId = String.format("%s_%s", yourId,
                                    heOrHerId);
                            ChatRoom chatRoom = ChatRoom.builder().chatId(
                                    String.valueOf(chatRoomServices.getChatId(yourId, heOrHerId, true)))
                                    .build();
                            Match matchByYou = Match.builder()
                                    .id(matchId)
                                    .yourId(yourId)
                                    .yourMatchId(heOrHerId)
                                    .matchRoom(chatRoom).
                                            build();
                            Match hisHerMatch = Match.builder()
                                    .id(matchId)
                                    .yourId(heOrHerId)
                                    .yourMatchId(yourId)
                                    .matchRoom(chatRoom)
                                    .build();
                            saveMatch(matchByYou);
                            saveMatch(hisHerMatch);

                            return Optional.of(matchId);
                        }
                );
    }

}
