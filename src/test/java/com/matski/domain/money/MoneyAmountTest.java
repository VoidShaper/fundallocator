package com.matski.domain.money;


import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyAmountTest {


    @Test
    public void moneyValueIsCorrect() {
        assertThat(new MoneyAmount(984).value()).isEqualTo(984);
        assertThat(new MoneyAmount(2123).value()).isEqualTo(2123);
    }

    @Test
    public void portionMoneyByEvenPercentage() {
        Percentage percentage = new Percentage(20);

        MoneyAmount moneyAmount = new MoneyAmount(1000);

        MoneyAmount result = moneyAmount.portionAsIntegralValueBy(percentage);

        assertThat(result).isEqualTo(new MoneyAmount(
                200
        ));
    }

    @Test
    public void portionUnevenMoneyByEvenPercentage() {
        Percentage percentage = new Percentage(10);

        MoneyAmount moneyAmount = new MoneyAmount(1001);

        MoneyAmount result = moneyAmount.portionAsIntegralValueBy(percentage);

        assertThat(result).isEqualTo(new MoneyAmount(
                100
        ));
    }

    @Test
    public void portionUnevenMoneyByUnevenPercentage() {
        Percentage percentage = new Percentage(17);

        MoneyAmount moneyAmount = new MoneyAmount(1001);

        MoneyAmount result = moneyAmount.portionAsIntegralValueBy(percentage);

        assertThat(result).isEqualTo(new MoneyAmount(
                170
        ));
    }

    @Test
    public void subtractsMoneyAmountFromAnother() {
        MoneyAmount moneyAmount = new MoneyAmount(1234);
        assertThat(moneyAmount.subtract(new MoneyAmount(123)))
                .isEqualTo(new MoneyAmount(1111));
    }

    @Test
    public void addsAnotherMoneyAmount() {
        MoneyAmount moneyAmount = new MoneyAmount(1234);
        assertThat(moneyAmount.add(new MoneyAmount(123)))
                .isEqualTo(new MoneyAmount(1357));
    }

    @Test
    public void splitFullValueIntoEvenGroups() {
        MoneyAmount moneyAmount = new MoneyAmount(1000);
        MoneyAmount expectedAmount = new MoneyAmount(250);
        List<MoneyAmount> moneyAmounts = moneyAmount.splitFullValueInto(4);
        assertThat(moneyAmounts).containsOnly(expectedAmount, expectedAmount, expectedAmount, expectedAmount);
    }

    @Test
    public void splitFullValueIntoGroupsWhereTheFirstIsLarger() {
        MoneyAmount moneyAmount = new MoneyAmount(1003);
        MoneyAmount expectedFirstAmount = new MoneyAmount(253);
        MoneyAmount expectedAmount = new MoneyAmount(250);
        List<MoneyAmount> moneyAmounts = moneyAmount.splitFullValueInto(4);
        assertThat(moneyAmounts).containsOnly(expectedFirstAmount, expectedAmount, expectedAmount, expectedAmount);
    }
}