package com.matski.domain.investments;

import com.google.common.collect.ImmutableList;
import com.matski.domain.funds.FundAllocation;
import com.matski.domain.money.MoneyAmount;

import java.util.Collection;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class AllocationResult {
    private final ImmutableList<FundAllocation> fundAllocations;
    private final MoneyAmount unallocatedAmount;

    public AllocationResult(Collection<FundAllocation> fundAllocations,
                            MoneyAmount unallocatedAmount) {
        this.fundAllocations = ImmutableList.copyOf(fundAllocations);
        this.unallocatedAmount = unallocatedAmount;
    }

    public ImmutableList<FundAllocation> fundAllocations() {
        return fundAllocations;
    }

    public MoneyAmount unallocatedAmount() {
        return unallocatedAmount;
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
