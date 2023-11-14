package com.playtech.enums;

public enum OperationType {
    DEPOSIT("DEPOSIT"),
    BET("BET"),
    WITHDRAW("WITHDRAW");
    
    private final String operationType;

    private OperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getMatchSide() {
        return operationType;
    }
}
