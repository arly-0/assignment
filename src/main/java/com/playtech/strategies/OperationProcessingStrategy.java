package com.playtech.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.playtech.enums.BetResults;
import com.playtech.enums.MatchSides;
import com.playtech.models.BetResult;
import com.playtech.models.Match;
import com.playtech.models.Operation;
import com.playtech.models.Player;

public class OperationProcessingStrategy {

    public static void processDepositOperation(Player player, Operation operation) {
        player.setBalance(player.getBalance() + operation.getCoinNumber());
        operation.setIsLegal(true);
    }

    public static void processBetOperation(Player player, Operation operation, Map<UUID, Match> matches) {
        if (operation.getCoinNumber() > player.getBalance()) {
            operation.setIsLegal(false);
        } else {
            operation.setIsLegal(true);
            player.setTotalBets(player.getTotalBets() + 1);

            Match match = matches.get(operation.getMatchUuid());

            if (match.getResult().equals(operation.getMatchSide())) {
                long playerProfit = (long) (operation.getCoinNumber()
                        * match.getReturnRateByPlayerSide(operation.getMatchSide()));

                player.setWonGames(player.getWonGames() + 1);
                player.setBalance((player.getBalance() + playerProfit));

                BetResult br = BetResult.builder().balanceChange(playerProfit)
                        .result(BetResults.WIN).build();
                List<BetResult> brs = player.getBetResults() != null ? player.getBetResults() : new ArrayList<>();
                brs.add(br);
                player.setBetResults(brs);
            } else if (match.getResult().equals(MatchSides.DRAW)) {
                player.setBalance(player.getBalance());
            } else {
                player.setBalance(player.getBalance() - operation.getCoinNumber());

                  BetResult br = BetResult.builder().balanceChange(operation.getCoinNumber())
                        .result(BetResults.LOSS).build();
                List<BetResult> brs = player.getBetResults();
                brs.add(br);
                player.setBetResults(brs);
            }
        }
    }

    public static void processWithdrawOperation(Player player, Operation operation) {
        if (operation.getCoinNumber() > player.getBalance()) {
            operation.setIsLegal(false);
        } else {
            player.setBalance(player.getBalance() - operation.getCoinNumber());
            operation.setIsLegal(true);
        }
    }

}
