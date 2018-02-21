package com.matski.infrastructure.persistence;

import com.matski.domain.funds.AvailableFundsTable;
import com.matski.domain.funds.AvailableFundsRepository;

public class InMemoryAvailableFundsRepository implements AvailableFundsRepository {

    private final AvailableFundsTable availableFundsTable;

    public InMemoryAvailableFundsRepository(AvailableFundsTable availableFundsTable) {
        this.availableFundsTable = availableFundsTable;
    }

    public AvailableFundsTable allAvailableFunds() {
        return availableFundsTable;
    }
}
