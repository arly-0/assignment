package com.playtech.strategies.operation;

public abstract class AbstractOperationProcessingStrategy<T> {
    public abstract T processDepositOperation(T player);

    public abstract T processBetOperation(T player);

    public abstract T processWithdrawOperation(T player);

}
