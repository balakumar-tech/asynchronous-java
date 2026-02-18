package com.jp.lld.urlshortner;

import java.math.BigInteger;

public class CapacityCalculator {

    public static void main(String[] args) {

        long reqPerSec = 10000;
        long reqPerYear = reqPerSec * 60 * 60 * 24 * 365;
        int numberOfYears  = 10;
        long totalReq = reqPerYear * numberOfYears;
        System.out.println(findClosestExponent(totalReq, 62));
    }

    public static int findClosestExponent(double number, int base) {

        double exponent = Math.log(number) / Math.log(base);

        int numberOfChars = (int)Math.floor(exponent);

        BigInteger bigBase = BigInteger.valueOf(base);
        BigInteger val = bigBase.pow(numberOfChars);

        BigInteger bigTarget = BigInteger.valueOf((long)number);
        while(val.compareTo(bigTarget) != 1) {
            numberOfChars++;
            val = bigBase.pow(numberOfChars);
        }
        return numberOfChars;
    }
}
