package com.matski.integration;

import com.matski.domain.funds.*;
import com.matski.domain.investments.AllocationResult;
import com.matski.domain.investments.InvestmentAllocator;
import com.matski.domain.investments.InvestmentStyle;
import com.matski.domain.money.MoneyAmount;
import com.matski.domain.money.Percentage;
import com.matski.infrastructure.persistence.InMemoryAvailableFundsRepository;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class FundsInvestmentIntegrationTest {

    private static final Fund POLISH_FUND1 = new Fund(new FundId(), "Polski 1", FundType.POLISH);
    private static final Fund POLISH_FUND2 = new Fund(new FundId(), "Polski 2", FundType.POLISH);
    private static final Fund POLISH_FUND3 = new Fund(new FundId(), "Polski 3", FundType.POLISH);
    private static final Fund FOREIGN_FUND1 = new Fund(new FundId(), "Zagraniczny 1", FundType.FOREIGN);
    private static final Fund FOREIGN_FUND2 = new Fund(new FundId(), "Zagraniczny 2", FundType.FOREIGN);
    private static final Fund FOREIGN_FUND3 = new Fund(new FundId(), "Zagraniczny 3", FundType.FOREIGN);
    private static final Fund MONETARY_FUND1 = new Fund(new FundId(), "Pieniężny 1", FundType.MONETARY);
    AvailableFundsRepository availableFundsRepository = new InMemoryAvailableFundsRepository(
            new AvailableFundsTable(asList(
                    POLISH_FUND1,
                    POLISH_FUND2,
                    FOREIGN_FUND1,
                    FOREIGN_FUND2,
                    FOREIGN_FUND3,
                    MONETARY_FUND1
            ))
    );

    AvailableFundsRepository availableFundsRepository2 = new InMemoryAvailableFundsRepository(
            new AvailableFundsTable(asList(
                    POLISH_FUND1,
                    POLISH_FUND2,
                    POLISH_FUND3,
                    FOREIGN_FUND1,
                    FOREIGN_FUND2,
                    MONETARY_FUND1
            ))
    );

    @Test
    public void testExampleSimple() {
        InvestmentAllocator investmentAllocator = new InvestmentAllocator(availableFundsRepository);
        AllocationResult allocationResult = investmentAllocator.allocate(new MoneyAmount(10000), InvestmentStyle.SAFE);

        assertThat(allocationResult).isEqualTo(
                new AllocationResult(
                        ImmutableList.of(
                                new FundAllocation(new MoneyAmount(1000), POLISH_FUND1, new Percentage(10)),
                                new FundAllocation(new MoneyAmount(1000), POLISH_FUND2, new Percentage(10)),
                                new FundAllocation(new MoneyAmount(2500), FOREIGN_FUND1, new Percentage(25)),
                                new FundAllocation(new MoneyAmount(2500), FOREIGN_FUND2, new Percentage(25)),
                                new FundAllocation(new MoneyAmount(2500), FOREIGN_FUND3, new Percentage(25)),
                                new FundAllocation(new MoneyAmount(500), MONETARY_FUND1, new Percentage(5))
                        ),
                        new MoneyAmount(0)
                )
        );
    }

    @Test
    public void testExampleRemainder() {

        InvestmentAllocator investmentAllocator = new InvestmentAllocator(availableFundsRepository);
        AllocationResult allocationResult = investmentAllocator.allocate(new MoneyAmount(10001), InvestmentStyle.SAFE);

        assertThat(allocationResult).isEqualTo(
                new AllocationResult(
                        ImmutableList.of(
                                new FundAllocation(new MoneyAmount(1000), POLISH_FUND1, new Percentage(10)),
                                new FundAllocation(new MoneyAmount(1000), POLISH_FUND2, new Percentage(10)),
                                new FundAllocation(new MoneyAmount(2500), FOREIGN_FUND1, new Percentage(25)),
                                new FundAllocation(new MoneyAmount(2500), FOREIGN_FUND2, new Percentage(25)),
                                new FundAllocation(new MoneyAmount(2500), FOREIGN_FUND3, new Percentage(25)),
                                new FundAllocation(new MoneyAmount(500), MONETARY_FUND1, new Percentage(5))
                        ),
                        new MoneyAmount(1)
                )
        );
    }

    @Test
    public void testExampleMoreComplexPercentage() {

        InvestmentAllocator investmentAllocator = new InvestmentAllocator(availableFundsRepository2);
        AllocationResult allocationResult = investmentAllocator.allocate(new MoneyAmount(10000), InvestmentStyle.SAFE);

        assertThat(allocationResult).isEqualTo(
                new AllocationResult(
                        ImmutableList.of(
                                new FundAllocation(new MoneyAmount(668), POLISH_FUND1, new Percentage(6.68)),
                                new FundAllocation(new MoneyAmount(666), POLISH_FUND2, new Percentage(6.66)),
                                new FundAllocation(new MoneyAmount(666), POLISH_FUND3, new Percentage(6.66)),
                                new FundAllocation(new MoneyAmount(3750), FOREIGN_FUND1, new Percentage(37.5)),
                                new FundAllocation(new MoneyAmount(3750), FOREIGN_FUND2, new Percentage(37.5)),
                                new FundAllocation(new MoneyAmount(500), MONETARY_FUND1, new Percentage(5))
                        ),
                        new MoneyAmount(0)
                )
        );
    }
}
