package com.playtech.models;

import java.util.List;
import java.util.UUID;

import com.playtech.enums.MatchSides;
import com.playtech.enums.OperationTypes;

public class Operation {
    private OperationTypes type;
    private UUID matchUuid;
    private int coinNumber;
    private MatchSides matchSide;
    private boolean isLegal;

    private Operation() {
    }

    public Builder builder() {
        return new Builder();
    }

    public static Operation fromString(List<String> operationAsString) {
        return new Operation().builder()
                .type(OperationTypes.valueOf(operationAsString.get(0)))
                .matchUuid(operationAsString.get(1))
                .coinNumber(Integer.parseInt(operationAsString.get(2)))
                .matchSide(operationAsString.size() >= 4 ? MatchSides.valueOf(operationAsString.get(3)) : null)
                .build();
    }

    public OperationTypes getOperationType() {
        return type;
    }

    public void setOperationType(OperationTypes operationType) {
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

    public MatchSides getMatchSide() {
        return matchSide;
    }

    public void setMatchSide(MatchSides matchSide) {
        this.matchSide = matchSide;
    }

    public boolean getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(boolean isLegal) {
        this.isLegal = isLegal;
    }

    public static class Builder {
        private Operation operation = new Operation();

        public Builder type(OperationTypes type) {
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

        public Builder matchSide(MatchSides matchSide) {
            operation.matchSide = matchSide;
            return this;
        }

        public Builder isLegal(boolean isLegal) {
            operation.isLegal = isLegal;
            return this;
        }

        public Operation build() {
            return operation;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %s",
                type,
                matchUuid != null ? matchUuid : "null",
                coinNumber,
                matchSide != null ? matchSide : "null");
    }
}
