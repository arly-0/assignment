package com.playtech.strategies.operation;

import java.util.Map;
import java.util.UUID;

import com.playtech.enums.MatchSide;
import com.playtech.models.Match;
import com.playtech.models.Operation;
import com.playtech.models.Player;

public class OperationProcessingStrategy {

    public static Player processDepositOperation(Player player, Operation operation) {
        player.setBalance(player.getBalance() + operation.getCoinNumber());
        operation.setIsLegal(true);

        player.setOperation(operation);
        return player;
    }

    public static Player processBetOperation(Player player, Operation operation, Map<UUID, Match> matches) {
        if (operation.getCoinNumber() > player.getBalance()) {
            operation.setIsLegal(false);
        } else {
            Match match = matches.get(operation.getMatchUuid());

            player.setTotalBets(player.getTotalBets() + 1);

            if (match.getResult().equals(operation.getMatchSide())) {
                player.setWonGames(player.getWonGames() + 1);
                player.setBalance((long) (player.getBalance()
                        + operation.getCoinNumber() * match.getReturnRateByPlayerSide(operation.getMatchSide())));
            } else if (match.getResult().equals(MatchSide.DRAW)) {
                player.setBalance(player.getBalance());
            } else {
                player.setBalance(player.getBalance() - operation.getCoinNumber());
            }
            operation.setIsLegal(true);
        }

        player.setOperation(operation);
        return player;
    }

    public static Player processWithdrawOperation(Player player, Operation operation) {
        if (operation.getCoinNumber() > player.getBalance()) {
            operation.setIsLegal(false);
        } else {
            player.setBalance(player.getBalance() - operation.getCoinNumber());
            operation.setIsLegal(true);
        }

        player.setOperation(operation);
        return player;
    }

}
