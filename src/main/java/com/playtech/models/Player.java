package com.playtech.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Player {
    private UUID uuid;
    private List<Operation> operations;
    private Long balance;
    private int wonGames;
    private int totalBets;
    private List<BetResult> betResults;

    private Player() {
    }

    public Builder builder() {
        return new Builder();
    }

    public static Player fromString(String playerAString) {
        String[] values = playerAString.split(",");

        Operation operation = Operation.fromString(Arrays.asList(values).subList(1, values.length));

        return new Player().builder()
                .uuid(UUID.fromString(values[0]))
                .operations(new ArrayList<>(Arrays.asList(operation)))
                .balance(0l)
                .wonGames(0)
                .totalBets(0)
                .build();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperation(List<Operation> operations) {
        this.operations = operations;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    public void incrementWonGames() {
        this.wonGames++;
    }

    public int getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(int totalBets) {
        this.totalBets = totalBets;
    }

    public void incrementTotalBets() {
        this.totalBets++;
    }

    public double calculateWinRate() {
        return (totalBets == 0) ? 0.0 : (double) wonGames / totalBets;
    }

     public List<BetResult> getBetResults() {
        return betResults;
    }

    public void setBetResults(List<BetResult> betResults) {
        this.betResults = betResults;
    }

    public static class Builder {
        private Player player = new Player();

        public Builder uuid(UUID uuid) {
            player.uuid = uuid;
            return this;
        }

        public Builder operations(List<Operation> operations) {
            player.operations = operations;
            return this;
        }

        public Builder balance(Long balance) {
            player.balance = balance;
            return this;
        }

        public Builder wonGames(int wonGames) {
            player.wonGames = wonGames;
            return this;
        }

        public Builder totalBets(int totalBets) {
            player.totalBets = totalBets;
            return this;
        }

        public Player build() {
            return player;
        }
    }
}
