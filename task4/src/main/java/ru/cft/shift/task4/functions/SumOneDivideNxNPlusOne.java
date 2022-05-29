package ru.cft.shift.task4.functions;

public class SumOneDivideNxNPlusOne implements Functions {
    //Cумма 1 / (n * (n + 1))

    @Override
    public double partialSum(long lowerValue, long upperValue) {
        double result = 0;
        for (double index = lowerValue; index <= upperValue; index++) {
            result = result + 1 / (index * (index + 1));
        }
        return result;
    }
}
