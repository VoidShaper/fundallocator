package com.matski.domain.investments;

import com.matski.domain.funds.AvailableFundsRepository;
import com.matski.domain.funds.AvailableFundsTable;
import com.matski.domain.funds.FundAllocation;
import com.matski.domain.funds.FundType;
import com.matski.domain.money.MoneyAmount;
import com.matski.domain.money.Percentage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvestmentAllocator {
    private final AvailableFundsRepository availableFundsRepository;

    public InvestmentAllocator(AvailableFundsRepository availableFundsRepository) {
        this.availableFundsRepository = availableFundsRepository;
    }

    public AllocationResult allocate(MoneyAmount totalMoneyAmount, InvestmentStyle investmentStyle) {

        AvailableFundsTable availableFundsTable = availableFundsRepository.allAvailableFunds();

        List<FundAllocation> allFundAllocations = new ArrayList<>();

        MoneyAmount alreadyAllocatedAmount = new MoneyAmount(0);
        for (Map.Entry<FundType, Percentage> investmentDistribution :
                investmentStyle.investmentDistribution().entrySet()) {

            Percentage percentageToDistribute = investmentDistribution.getValue();
            FundType fundTypeToAllocateTo = investmentDistribution.getKey();

            MoneyAmount amountToAllocate = totalMoneyAmount
                    .portionAsIntegralValueBy(percentageToDistribute);

            allFundAllocations.addAll(availableFundsTable.allocate(
                    amountToAllocate,
                    fundTypeToAllocateTo,
                    percentageToDistribute
            ));

            alreadyAllocatedAmount = alreadyAllocatedAmount.add(amountToAllocate);
        }
        MoneyAmount unallocatedAmount = totalMoneyAmount.subtract(alreadyAllocatedAmount);
        return new AllocationResult(
                allFundAllocations,
                unallocatedAmount
        );
    }
}
