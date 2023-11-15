package com.playtech;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.playtech.models.Match;
import com.playtech.models.Player;
import com.playtech.processors.PlayerProcessor;
import com.playtech.utils.Utils;

public class Main {
    public static void main(String[] args) {
        Long hostBalance = 0l;
        List<String> playerData = Utils.readData("src/main/resources/player_data.txt");
        List<String> matchData = Utils.readData("src/main/resources/match_data.txt");

        Map<UUID, Match> matches = matchData.stream()
                .map(Match::fromString)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Match::getUuid, Function.identity()));

        Map<UUID, Player> players = PlayerProcessor.processPlayersOperations(playerData, matches);
        
        List<String> result = Utils.createResultString(players);
        Utils.writeResult(result, "src/main/java/com/playtech/result.txt");
    }
}
