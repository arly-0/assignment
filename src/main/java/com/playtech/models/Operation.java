package com.playtech.models;

import java.util.List;
import java.util.UUID;

import com.playtech.enums.MatchSide;
import com.playtech.enums.OperationType;

public class Operation {
    private OperationType type;
    private UUID matchUuid;
    private int coinNumber;
    private MatchSide matchSide;

    private Operation() {
    }

    public Builder builder() {
        return new Builder();
    }

    public static Operation fromString(List<String> operationAsString) {
        return new Operation().builder()
                .type(OperationType.valueOf(operationAsString.get(0)))
                .matchUuid(operationAsString.get(1))
                .coinNumber(Integer.parseInt(operationAsString.get(2)))
                .matchSide(operationAsString.size() >= 4 ? MatchSide.valueOf(operationAsString.get(3)) : null)
                .build();
    }

    public OperationType getOperationType() {
        return type;
    }

    public void setOperationType(OperationType operationType) {
        this.type = operationType;
    }

    public UUID getMatchUuid() {
        return matchUuid;
    }

    public void setMatchUuid(UUID matchUuid) {
        this.matchUuid = matchUuid;
    }

    public int getCoinNumber() {
        return coinNumber;
    }

    public void setCoinNumber(int coinNumber) {
        this.coinNumber = coinNumber;
    }

    public MatchSide getMatchSide() {
        return matchSide;
    }

    public void setMatchSide(MatchSide matchSide) {
        this.matchSide = matchSide;
    }

    public static class Builder {
        private Operation operation = new Operation();

        public Builder type(OperationType type) {
            operation.type = type;
            return this;
        }

        public Builder matchUuid(String matchUuid) {
            try {
                operation.matchUuid = UUID.fromString(matchUuid);
            } catch (Exception e) {
                operation.matchUuid = null;
            }
            return this;
        }

        public Builder coinNumber(int coinNumber) {
            operation.coinNumber = coinNumber;
            return this;
        }

        public Builder matchSide(MatchSide matchSide) {
            operation.matchSide = matchSide;
            return this;
        }

        public Operation build() {
            return operation;
        }
    }
}
