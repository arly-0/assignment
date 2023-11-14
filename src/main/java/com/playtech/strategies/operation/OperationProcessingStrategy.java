package com.playtech.strategies.operation;

import com.playtech.models.Operation;
import com.playtech.models.Player;

public class OperationProcessingStrategy {

    public static Player processDepositOperation(Player player, Operation operation) {
        player.setBalance(player.getBalance() + operation.getCoinNumber());
        return player;
    }

    public static Player processBetOperation(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processBetOperation'");
    }

    public static Player processWithdrawOperation(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processWithdrawOperation'");
    }

}
