package com.playtech.processors;

import java.util.List;

import com.playtech.models.BetResult;
import com.playtech.models.Casino;
import com.playtech.models.Player;
import com.playtech.strategies.CasinoProcessingStrategy;

public class CasinoProcessor {
    public static void process(List<Player> players, Casino casino) {
        players.stream()
                .forEach(player -> player.getBetResults().stream()
                        .forEach(betResult -> processCasinoBalance(betResult, casino)));
    }

    private static void processCasinoBalance(BetResult betResult, Casino casino) {
        switch (betResult.getBetResult()) {
            case WIN:
                CasinoProcessingStrategy.processCasinoBalancePlayerWin(betResult, casino);
                break;
            case LOSS:
                CasinoProcessingStrategy.processCasinoBalancePlayerLoss(betResult, casino);
                break;
            default:
                break;
        }
    }
}
