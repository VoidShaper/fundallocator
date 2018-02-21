package com.matski.application;

import com.matski.domain.funds.*;
import com.matski.domain.investments.AllocationResult;
import com.matski.domain.investments.InvestmentAllocator;
import com.matski.domain.investments.InvestmentStyle;
import com.matski.domain.money.MoneyAmount;
import com.matski.infrastructure.persistence.InMemoryAvailableFundsRepository;

import static java.util.Arrays.asList;

public class MainApp {

    public static void main(String[] args) {
        AvailableFundsRepository availableFundsRepository = new InMemoryAvailableFundsRepository(
                new AvailableFundsTable(asList(
                        new Fund(new FundId(), "Polski 1", FundType.POLISH),
                        new Fund(new FundId(), "Polski 2", FundType.POLISH),
                        new Fund(new FundId(), "Zagraniczny 1", FundType.FOREIGN),
                        new Fund(new FundId(), "Zagraniczny 2", FundType.FOREIGN),
                        new Fund(new FundId(), "Zagraniczny 3", FundType.FOREIGN),
                        new Fund(new FundId(), "Pieniężny 1", FundType.MONETARY)
                ))
        );

        AvailableFundsRepository availableFundsRepository2 = new InMemoryAvailableFundsRepository(
                new AvailableFundsTable(asList(
                        new Fund(new FundId(), "Polski 1", FundType.POLISH),
                        new Fund(new FundId(), "Polski 2", FundType.POLISH),
                        new Fund(new FundId(), "Polski 3", FundType.POLISH),
                        new Fund(new FundId(), "Zagraniczny 1", FundType.FOREIGN),
                        new Fund(new FundId(), "Zagraniczny 2", FundType.FOREIGN),
                        new Fund(new FundId(), "Pieniężny 1", FundType.MONETARY)
                ))
        );

        InvestmentAllocator investmentAllocator = new InvestmentAllocator(availableFundsRepository2);

        AllocationResult allocationResult = investmentAllocator.allocate(
                new MoneyAmount(10001),
                InvestmentStyle.SAFE);

        for(FundAllocation allocation : allocationResult.fundAllocations()) {
            System.out.println(String.format("Allocation: type %s | name %s | amount %s PLN | percentage %s %%",
                    allocation.fund().type(),
                    allocation.fund().name(),
                    allocation.moneyAmount(),
                    allocation.allocatedPercentage()
            ));
        }
        System.out.println(String.format("Unallocated amount: %s", allocationResult.unallocatedAmount()));
    }
}
