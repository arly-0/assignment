package com.playtech;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.playtech.models.Casino;
import com.playtech.models.Match;
import com.playtech.models.Player;
import com.playtech.processors.CasinoProcessor;
import com.playtech.processors.PlayerProcessor;
import com.playtech.utils.Utils;

public class Main {
    public static void main(String[] args) throws IOException {
        Casino casino = new Casino();
        List<Player> players = Utils.readPlayers("src/main/resources/player_data.txt");
        Map<UUID, Match> matches = Utils.readMatches("src/main/resources/match_data.txt");

        PlayerProcessor.process(players, matches);

        List<Player> legalPlayers = Utils.filterOutIllegalPlayers(players);
        List<Player> illegalPlayers = Utils.filterOutLegalPlayers(players, legalPlayers);

        CasinoProcessor.process(legalPlayers, casino);

        List<String> result = Utils.createResultString(legalPlayers, illegalPlayers, casino);
        Utils.writeResult(result, "src/main/java/com/playtech/result.txt");

    }
}
