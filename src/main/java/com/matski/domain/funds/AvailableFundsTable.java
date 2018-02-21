package com.matski.domain.funds;

import com.matski.domain.money.MoneyAmount;
import com.matski.domain.money.Percentage;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class AvailableFundsTable {
    private final ImmutableList<Fund> allFunds;

    public AvailableFundsTable(Collection<Fund> allFunds) {
        this.allFunds = ImmutableList.copyOf(allFunds);
    }

    public List<FundAllocation> allocate(MoneyAmount amountToAllocate,
                                         FundType fundType,
                                         Percentage allocatedPercentage) {
        List<Fund> fundsWithRightType = allFunds.stream()
                .filter(fund -> fund.isOfType(fundType))
                .collect(Collectors.toList());

        List<MoneyAmount> allocatedMoneyAmounts = amountToAllocate.splitFullValueInto(fundsWithRightType.size());

        return IntStream.range(0, fundsWithRightType.size())
                .mapToObj(i -> new FundAllocation(allocatedMoneyAmounts.get(i),
                        fundsWithRightType.get(i),
                        allocatedPercentage.splitByValue(allocatedMoneyAmounts.get(i).value(),
                                amountToAllocate.value())))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
