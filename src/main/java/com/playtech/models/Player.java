package com.playtech.models;

import java.util.Arrays;
import java.util.UUID;

public class Player {
    private UUID uuid;
    private Operation operation;
    private Long balance;
    private int wonGames;
    private int totalBets;

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
                .operation(operation)
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

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
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

    public static class Builder {
        private Player player = new Player();

        public Builder uuid(UUID uuid) {
            player.uuid = uuid;
            return this;
        }

        public Builder operation(Operation operation) {
            player.operation = operation;
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
