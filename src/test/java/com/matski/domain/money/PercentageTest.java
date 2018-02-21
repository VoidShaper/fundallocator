package com.matski.domain.money;


import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PercentageTest {

    @Test
    public void percentageValueIsCorrect() {
        BigDecimal value = new BigDecimal("98");
        Percentage percentage = new Percentage(98);
        assertThat(percentage.value()).isEqualTo(value);
    }

    @Test
    public void percentageSplitByValueIsCorrect() {
        Percentage percentage = new Percentage(20);
        assertThat(percentage.splitByValue(666, 2000))
                .isEqualTo(new Percentage(6.66));
        assertThat(percentage.splitByValue(668, 2000))
                .isEqualTo(new Percentage(6.68));
    }
}