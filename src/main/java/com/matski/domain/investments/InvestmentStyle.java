package com.matski.domain.investments;

import com.matski.domain.funds.FundType;
import com.matski.domain.money.Percentage;
import com.google.common.collect.ImmutableMap;

public enum InvestmentStyle {
    SAFE(ImmutableMap.of(
            FundType.POLISH, new Percentage(20),
            FundType.FOREIGN, new Percentage(75),
            FundType.MONETARY, new Percentage(5)
    )),
    BALANCED(ImmutableMap.of(
            FundType.POLISH, new Percentage(30),
            FundType.FOREIGN, new Percentage(60),
            FundType.MONETARY, new Percentage(10)
    )),
    AGGRESIVE(ImmutableMap.of(
            FundType.POLISH, new Percentage(40),
            FundType.FOREIGN, new Percentage(20),
            FundType.MONETARY, new Percentage(40)
    ));

    private final ImmutableMap<FundType, Percentage> investmentDistribution;

    InvestmentStyle(ImmutableMap<FundType, Percentage> investmentDistribution) {
        this.investmentDistribution = investmentDistribution;
    }

    public ImmutableMap<FundType, Percentage> investmentDistribution() {
        return investmentDistribution;
    }
}
