package com.matski.domain.funds;

import com.matski.domain.money.MoneyAmount;
import com.matski.domain.money.Percentage;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class FundAllocation {

    private final MoneyAmount moneyAmount;
    private final Fund fund;
    private final Percentage allocatedPercentage;

    public FundAllocation(MoneyAmount moneyAmount,
                          Fund fund,
                          Percentage allocatedPercentage) {
        this.moneyAmount = moneyAmount;
        this.fund = fund;
        this.allocatedPercentage = allocatedPercentage;
    }

    public MoneyAmount moneyAmount() {
        return moneyAmount;
    }

    public Fund fund() {
        return fund;
    }

    public Percentage allocatedPercentage() {
        return allocatedPercentage;
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
