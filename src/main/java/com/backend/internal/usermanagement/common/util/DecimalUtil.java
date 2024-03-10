package com.backend.internal.usermanagement.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DecimalUtil {

    public BigDecimal roundUp(double data) {
        BigDecimal value = BigDecimal.valueOf(data);
        // round to 2 decimal places
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal roundUp(BigDecimal data) {
        BigDecimal value = data;
        // round to 2 decimal places
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
