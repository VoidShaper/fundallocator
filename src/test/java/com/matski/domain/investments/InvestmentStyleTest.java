package com.matski.domain.investments;


import com.matski.domain.funds.FundType;
import com.matski.domain.money.Percentage;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InvestmentStyleTest {

    @Test
    public void safeInvestmentStyleTest() {
        assertThat(InvestmentStyle.SAFE.investmentDistribution())
                .isEqualTo(
                        ImmutableMap.of(
                                FundType.POLISH, new Percentage(20),
                                FundType.FOREIGN, new Percentage(75),
                                FundType.MONETARY, new Percentage(5)
                        )
                );
    }

    @Test
    public void balancedInvestmentStyleTest() {
        assertThat(InvestmentStyle.BALANCED.investmentDistribution())
                .isEqualTo(
                        ImmutableMap.of(
                                FundType.POLISH, new Percentage(30),
                                FundType.FOREIGN, new Percentage(60),
                                FundType.MONETARY, new Percentage(10)
                        ));
    }

    @Test
    public void aggressiveInvestmentStyleTest() {
        assertThat(InvestmentStyle.AGGRESIVE.investmentDistribution())
                .isEqualTo(
                        ImmutableMap.of(
                                FundType.POLISH, new Percentage(40),
                                FundType.FOREIGN, new Percentage(20),
                                FundType.MONETARY, new Percentage(40)
                        )
                );
    }
}