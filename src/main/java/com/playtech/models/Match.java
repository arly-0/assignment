package com.playtech.models;

import java.util.UUID;

import com.playtech.enums.MatchSides;

public class Match {
    private UUID uuid;
    private double returnRateA;
    private double returnRateB;
    private MatchSides result;

    private Match() {
    }

    public Builder builder() {
        return new Builder();
    }

    public static Match fromString(String matchAsString) {
        String[] matchValues = matchAsString.split(",");

        UUID uuid = UUID.fromString(matchValues[0]);
        double returnRateA = Double.parseDouble(matchValues[1]);
        double returnRateB = Double.parseDouble(matchValues[2]);
        MatchSides result = MatchSides.valueOf(matchValues[3]);

        return new Match().builder()
                .uuid(uuid)
                .returnRateA(returnRateA)
                .returnRateB(returnRateB)
                .result(result)
                .build();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public double getReturnRateA() {
        return returnRateA;
    }

    public void setReturnRateA(double returnRateA) {
        this.returnRateA = returnRateA;
    }

    public double getReturnRateB() {
        return returnRateB;
    }

    public double getReturnRateByPlayerSide(MatchSides matchSide) {
        double returnRate = 0f;
        if (matchSide.equals(MatchSides.A)) {
            returnRate = returnRateA;
        } else if (matchSide.equals(MatchSides.B)) {
            returnRate = returnRateB;
        }
        return returnRate;
    }

    public void setReturnRateB(double returnRateB) {
        this.returnRateB = returnRateB;
    }

    public MatchSides getResult() {
        return result;
    }

    public void setResult(MatchSides result) {
        this.result = result;
    }

    public double getReturnRate(MatchSides side) {
        switch (side) {
            case A:
                return returnRateA;
            case B:
                return returnRateB;
            default:
                throw new IllegalArgumentException("Invalid side: " + side);
        }
    }

    public static class Builder {
        private Match match = new Match();

        public Builder uuid(UUID uuid) {
            match.uuid = uuid;
            return this;
        }

        public Builder returnRateA(double returnRateA) {
            match.returnRateA = returnRateA;
            return this;
        }

        public Builder returnRateB(double returnRateB) {
            match.returnRateB = returnRateB;
            return this;
        }

        public Builder result(MatchSides result) {
            match.result = result;
            return this;
        }

        public Match build() {
            return match;
        }
    }
}
