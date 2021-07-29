package com.kmozaid.java.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class MonetaryValue {

    public static void main(String[] args) {
        System.out.println(1000000d + 1.2d - 1000000d); // This is why we can't use double or float.
        System.out.println(1.03 - .42);                 // This should be 0.61 precisely.

        BigDecimal amount = new BigDecimal(1.03);
        BigDecimal spent = new BigDecimal(.42);

        System.out.println(amount.subtract(spent).add(spent).round(MathContext.DECIMAL32) + "\n");

        // floating point calculation
        final double amount1 = 2.0;
        final double amount2 = 1.1;
        System.out.println("difference between 2.0 and 1.1 using double is: " + (amount1 - amount2));

        // Use BigDecimal for financial calculation
        final BigDecimal amount3 = new BigDecimal("2.0");
        final BigDecimal amount4 = new BigDecimal("1.1");
        System.out.println("difference between 2.0 and 1.1 using BigDecimal is: " + (amount3.subtract(amount4)) + "\n");


        int scale = 6;
        double value = 22.121455;
        BigDecimal tempBig = new BigDecimal(Double.toString(value));
        tempBig = tempBig.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        String strValue = tempBig.stripTrailingZeros().toPlainString();
        System.out.println("tempBig = " + strValue);

        System.out.println("tempBig = " + formatRupees(tempBig.doubleValue()));
    }

    public static String formatRupees(double value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(5);
        format.setRoundingMode(RoundingMode.HALF_EVEN);
        return format.format(value);
    }
}
