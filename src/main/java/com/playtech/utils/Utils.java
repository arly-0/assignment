package com.playtech.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.playtech.models.Match;
import com.playtech.models.Player;

public class Utils {
    public static List<Player> readPlayers(String filePath) throws IOException {
        List<Player> players = new ArrayList<>();
        Map<UUID, Player> playerMap = new HashMap<>();
        List<String> lines = Files.readAllLines(Path.of(filePath));

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            Player newPlayer = Player.fromString(line);
            UUID uuid = newPlayer.getUuid();

            if (!playerMap.containsKey(uuid)) {
                playerMap.put(uuid, newPlayer);
                players.add(newPlayer);
            } else {
                playerMap.get(uuid).getOperations().add(newPlayer.getOperations().get(0));
            }
        }

        return players;
    }

    public static Map<UUID, Match> readMatches(String filePath) throws IOException {
       return Files.readAllLines(Path.of(filePath)).stream()
                .map(Match::fromString)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Match::getUuid, Function.identity()));
    }

    public static void writeResult(List<String> results, String fileName) {
        try {
            Files.write(Path.of(fileName), results);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }

    // public static List<String> createResultString(Map<UUID, Player> players) {
    // List<String> results = new ArrayList<>();

    // // List of legitimate players
    // players.values().stream()
    // .filter(player -> player.getOperation().getIsLegal() == true)
    // .sorted(Comparator.comparing(Player::getUuid))
    // .forEach(
    // player -> results.add(player.getUuid().toString() + " " +
    // player.getBalance().toString() + " " +
    // String.format(Locale.US, "%.2f", player.calculateWinRate())));

    // results.add("");

    // // List of illegitimate players
    // players.values().stream()
    // .filter(player -> player.getOperation().getIsLegal() == false)
    // .sorted(Comparator.comparing(Player::getUuid))
    // .forEach(player -> results.add(player.getUuid().toString() + " " +
    // player.getOperation().toString()));

    // // Casino balance
    // // results.add("");
    // // results.add(String.valueOf(casinoBalanceChange));

    // return results;
    // }
}
