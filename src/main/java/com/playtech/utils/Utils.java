package com.playtech.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.playtech.models.Player;

public class Utils {
    public static List<String> readData(String fileName) {
        try {
            return Files.lines(Path.of(fileName))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
            return List.of();
        }
    }

    public static void writeResult(List<String> results, String fileName) {
        try {
            Files.write(Path.of(fileName), results);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }

    public static List<String> createResultString(Map<UUID, Player> players) {
        List<String> results = new ArrayList<>();

        // List of legitimate players
        players.values().stream()
                .filter(player -> player.getOperation().getIsLegal() == true)
                .sorted(Comparator.comparing(Player::getUuid))
                .forEach(
                        player -> results.add(player.getUuid().toString() + " " + player.getBalance().toString() + " " +
                                String.format(Locale.US, "%.2f", player.calculateWinRate())));

        results.add("");

        // List of illegitimate players
        players.values().stream()
                .filter(player -> player.getOperation().getIsLegal() == false)
                .sorted(Comparator.comparing(Player::getUuid))
                .forEach(player -> results.add(player.getUuid().toString() + " " + player.getOperation().toString()));

        // Casino balance
        results.add("");
        // int casinoBalanceChange = calculateCasinoBalanceChange(players);
        // results.add(String.valueOf(casinoBalanceChange));

        return results;
    }
}
