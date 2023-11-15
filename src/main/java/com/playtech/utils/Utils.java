package com.playtech.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.playtech.models.Casino;
import com.playtech.models.Match;
import com.playtech.models.Operation;
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

    public static List<String> createResultString(List<Player> legalPlayers, List<Player> illegalPlayers,
            Casino casino) {
        List<String> results = new ArrayList<>();

        legalPlayers.stream()
                .sorted(Comparator.comparing(Player::getUuid))
                .forEach(player -> results.add(player.getUuid().toString() + " " +
                        player.getBalance().toString() + " " +
                        String.format(Locale.US, "%.2f", player.calculateWinRate())));

        results.add("");

        illegalPlayers.stream()
                .sorted(Comparator.comparing(Player::getUuid))
                .forEach(player -> results.add(player.getUuid().toString() + " "
                        + player.getOperations().stream().filter(operation -> operation.getIsLegal() == false)
                                .map(Operation::toString).collect(Collectors.joining(" "))));

        results.add("");

        results.add(String.valueOf(casino.getBalance()));

        return results;
    }

    public static List<Player> filterOutIllegalPlayers(List<Player> players) {
        return players.stream()
                .filter(player -> player.getOperations().stream()
                        .noneMatch(operation -> operation.getIsLegal() == false))
                .collect(Collectors.toList());
    }

    public static List<Player> filterOutLegalPlayers(List<Player> players, List<Player> legalPlayers) {
        return players.stream()
                .filter(player -> !legalPlayers.contains(player))
                .collect(Collectors.toList());
    }
}
