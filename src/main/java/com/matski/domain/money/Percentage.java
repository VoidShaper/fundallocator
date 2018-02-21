package com.matski.domain.money;

import java.math.BigDecimal;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class Percentage {
    private final double value;

    public Percentage(double value) {
        this.value = value;
    }

    public BigDecimal value() {
        return new BigDecimal(value);
    }

    public Percentage splitByValue(int splitValue, int totalValue) {
        return new Percentage(value * splitValue / totalValue);
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
        return String.format("%.2f", value);
    }
}
