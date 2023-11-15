package com.playtech.processors;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.playtech.models.Match;
import com.playtech.models.Operation;
import com.playtech.models.Player;
import com.playtech.strategies.OperationProcessingStrategy;

public class PlayerProcessor {

    public static void process(List<Player> players, Map<UUID, Match> matches) {
        players.forEach(player -> player.getOperations()
                .forEach(operation -> processPlayerOperation(player, operation, matches)));
    }

    private static void processPlayerOperation(Player player, Operation operation, Map<UUID, Match> matches) {
        switch (operation.getOperationType()) {
            case DEPOSIT:
                OperationProcessingStrategy.processDepositOperation(player, operation);
                break;
            case BET:
                OperationProcessingStrategy.processBetOperation(player, operation, matches);
                break;
            case WITHDRAW:
                OperationProcessingStrategy.processWithdrawOperation(player, operation);
                break;
            default:
                break;
        }
    }
}
