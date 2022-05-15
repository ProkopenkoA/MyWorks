package ru.cft.shift.task4.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.shift.task4.functions.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculatorFunction {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorFunction.class);

    private Functions functions;
    private static double result = 0;
    private int countOfFlow = 3;

    public CalculatorFunction() {
    }

    public void setFunctions(Functions functions) {
        this.functions = functions;
    }

    public void setCountOfFlow(int countOfFlow) {
        this.countOfFlow = countOfFlow;
    }

    public static void addToResult(double term) {
        result = result + term;
    }

    public double calculateFunctionSum(long firstNumber, long upperBound) throws Exception {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<Double>> callables = new ArrayList<>();
        long[] bound = new long[countOfFlow + 1];

        bound[0] = firstNumber;
        for (int index = 1; index < countOfFlow + 1; index++) {
            bound[index] = upperBound / countOfFlow * index;
        }
        bound[countOfFlow] = upperBound + 1;

        for (int index = 0; index < countOfFlow; index++) {
            int finalIndex = index;
            callables.add(index,
                    () -> {
                        LOGGER.info("Запуск потока - " + Thread.currentThread().getName());
                        return functions.partialSum(bound[finalIndex], bound[finalIndex + 1] - 1);
                    });
        }

        try {
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(CalculatorFunction::addToResult);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

}
