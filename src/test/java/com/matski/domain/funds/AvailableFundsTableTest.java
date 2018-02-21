package com.matski.domain.funds;

import com.google.common.collect.ImmutableList;
import com.matski.domain.money.MoneyAmount;
import com.matski.domain.money.Percentage;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AvailableFundsTableTest {

    private static final Fund POLISH_FUND1 = new Fund(new FundId(), "Polski 1", FundType.POLISH);
    private static final Fund POLISH_FUND2 = new Fund(new FundId(), "Polski 2", FundType.POLISH);
    private static final Fund FOREIGN_FUND1 = new Fund(new FundId(), "Zagraniczny 1", FundType.FOREIGN);
    private static final Fund FOREIGN_FUND2 = new Fund(new FundId(), "Zagraniczny 2", FundType.FOREIGN);
    private static final Fund FOREIGN_FUND3 = new Fund(new FundId(), "Zagraniczny 2", FundType.FOREIGN);
    private static final Fund MONETARY_FUND1 = new Fund(new FundId(), "Pieniężny 1", FundType.MONETARY);

    private final AvailableFundsTable availableFundsTable = new AvailableFundsTable(
            ImmutableList.of(
                    FOREIGN_FUND1,
                    POLISH_FUND1,
                    FOREIGN_FUND3,
                    MONETARY_FUND1,
                    POLISH_FUND2,
                    FOREIGN_FUND2
            )
    );

    @Test
    public void allocatesFundsForSingleFund() {
        List<FundAllocation> allocations = availableFundsTable.allocate(
                new MoneyAmount(456),
                FundType.MONETARY,
                new Percentage(15.15)
        );


        assertThat(allocations).containsOnly(
                new FundAllocation(
                        new MoneyAmount(456),
                        MONETARY_FUND1,
                        new Percentage(15.15)
                ));
    }

    @Test
    public void allocatesFundsBasedOnTypeForEvenAmount() {
        List<FundAllocation> allocations = availableFundsTable.allocate(
                new MoneyAmount(300),
                FundType.POLISH,
                new Percentage(30)
        );

        assertThat(allocations).containsOnly(
                new FundAllocation(
                        new MoneyAmount(150),
                        POLISH_FUND1,
                        new Percentage(15)
                ),
                new FundAllocation(
                        new MoneyAmount(150),
                        POLISH_FUND2,
                        new Percentage(15)
                )
        );
    }

    @Test
    public void allocatesFundsBasedOnTypeForUnevenAmount() {
        List<FundAllocation> allocations = availableFundsTable.allocate(
                new MoneyAmount(2000),
                FundType.FOREIGN,
                new Percentage(20)
        );

        assertThat(allocations).containsOnly(
                new FundAllocation(
                        new MoneyAmount(668),
                        FOREIGN_FUND1,
                        new Percentage(6.68)
                ),
                new FundAllocation(
                        new MoneyAmount(666),
                        FOREIGN_FUND2,
                        new Percentage(6.66)
                ),
                new FundAllocation(
                        new MoneyAmount(666),
                        FOREIGN_FUND3,
                        new Percentage(6.66)
                )
        );
    }
}