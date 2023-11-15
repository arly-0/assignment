package com.playtech.models;

import com.playtech.enums.BetResults;

public class BetResult {
    private long balanceChange;
    private BetResults result;

    private BetResult() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getBalanceChange() {
        return balanceChange;
    }

    public void setBalanceChange(long balanceChange) {
        this.balanceChange = balanceChange;
    }

    public BetResults getBetResult() {
        return result;
    }

    public void setBetResult(BetResults betResult) {
        this.result = betResult;
    }

    public static class Builder {
        private BetResult betResult = new BetResult();

        public Builder balanceChange(long balanceChange) {
            betResult.balanceChange = balanceChange;
            return this;
        }

        public Builder result(BetResults result) {
            betResult.result = result;
            return this;
        }

        public BetResult build() {
            return betResult;
        }
    }
}
