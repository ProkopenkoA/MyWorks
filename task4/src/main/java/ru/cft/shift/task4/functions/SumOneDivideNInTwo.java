package ru.cft.shift.task4.functions;

public class SumOneDivideNInTwo implements Functions {
    //Сумма 1 / n^2

    @Override
    public double partialSum(long lowerValue, long upperValue) {
        double result = 0;
        for (double index = lowerValue; index <= upperValue; index++) {
            result = result + 1 / (index * index);
        }
        return result;
    }
}
