package com.matski.domain.money;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class MoneyAmount {
    private final BigDecimal value;

    public MoneyAmount(int value) {
        this.value = new BigDecimal(value);
    }

    public MoneyAmount portionAsIntegralValueBy(Percentage percentage) {
        return new MoneyAmount(value.multiply(percentage.value()).divideToIntegralValue(new BigDecimal(100)).intValue());
    }

    public MoneyAmount subtract(MoneyAmount moneyAmount) {
        return new MoneyAmount(value.subtract(moneyAmount.value).intValue());
    }

    public MoneyAmount add(MoneyAmount moneyAmount) {
        return new MoneyAmount(value.add(moneyAmount.value).intValue());
    }

    public List<MoneyAmount> splitFullValueInto(int amounts) {
        BigDecimal[] divisionResult = value
                .divideAndRemainder(new BigDecimal(amounts));
        return IntStream.range(0, amounts)
                .mapToObj(i -> i == 0 ? divisionResult[0].add(divisionResult[1]).intValue() : divisionResult[0].intValue())
                .map(MoneyAmount::new)
                .collect(Collectors.toList());
    }

    public int value() {
        return value.intValue();
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
        return String.format("%d", value.intValue());
    }
}
