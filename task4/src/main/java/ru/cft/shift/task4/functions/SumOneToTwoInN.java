package ru.cft.shift.task4.functions;

public class SumOneToTwoInN implements Functions {
    //Cумма 1 / 2^n

    @Override
    public double partialSum(long lowerValue, long upperValue) {
        double result = 0;
        for (double index = lowerValue; index <= upperValue; index++) {
            result = result + 1 / (Math.pow(2, index));
        }
        return result;
    }
}
