package com.playtech.enums;

public enum OperationTypes {
    DEPOSIT("DEPOSIT"),
    BET("BET"),
    WITHDRAW("WITHDRAW");
    
    private final String operationType;

    private OperationTypes(String operationType) {
        this.operationType = operationType;
    }

    public String getMatchSide() {
        return operationType;
    }
}
