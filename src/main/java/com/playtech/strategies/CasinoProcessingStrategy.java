package com.playtech.strategies;

import com.playtech.models.BetResult;
import com.playtech.models.Casino;

public class CasinoProcessingStrategy {
    public static void processCasinoBalancePlayerWin(BetResult betResult, Casino casino) {
        casino.setBalance(casino.getBalance() - betResult.getBalanceChange());
    }

    public static void processCasinoBalancePlayerLoss(BetResult betResult, Casino casino) {
        casino.setBalance(casino.getBalance() + betResult.getBalanceChange());
    }
}
