package com.matski.domain.funds;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class Fund {

    private final FundId id;
    private final String name;
    private final FundType type;

    public Fund(FundId id, String name, FundType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public FundId getId() {
        return id;
    }

    public String name() {
        return name;
    }

    public FundType type() {
        return type;
    }

    public boolean isOfType(FundType fundType) {
        return this.type == fundType;
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
