package com.playtech.processors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.playtech.models.Match;
import com.playtech.models.Operation;
import com.playtech.models.Player;
import com.playtech.strategies.operation.OperationProcessingStrategy;

public class PlayerProcessor {

    public static Map<UUID, Player> processPlayersOperations(List<String> playerData, Map<UUID, Match> matches) {
        Map<UUID, Player> players = new HashMap<>();

        for (String playerAsString : playerData) {
            try {
                Player player = Player.fromString(playerAsString);
                UUID playerUuid = player.getUuid();

                if (players.containsKey(playerUuid)) {
                    Player existingPlayer = players.get(playerUuid);
                    players.put(existingPlayer.getUuid(), processPlayerOperation(existingPlayer, player.getOperation()));
                } else {
                    players.put(playerUuid, processPlayerOperation(player, player.getOperation()));
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Skipping invalid player data: " + playerAsString);
                e.printStackTrace();
            }
        }

        return players;
    }

    public static Player processPlayerOperation(Player player, Operation operation) {
        switch (operation.getOperationType()) {
            case DEPOSIT:
                return OperationProcessingStrategy.processDepositOperation(player, operation);
            default:
                break;
        }
        return player;
    }
}
