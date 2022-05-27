package ru.cft.shift.task4;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.shift.task4.functions.Functions;
import ru.cft.shift.task4.functions.SumOneDivideNxNPlusOne;
import ru.cft.shift.task4.io.ConsoleReader;
import ru.cft.shift.task4.model.CalculatorFunction;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Functions functions = new SumOneDivideNxNPlusOne();
        ConsoleReader consoleReader = new ConsoleReader();
        CalculatorFunction calculatorFunction = new CalculatorFunction(Runtime.getRuntime().availableProcessors());

        calculatorFunction.setFunctions(functions);

        long upperBound;
        double result;
        upperBound = consoleReader.consoleReadNumber();
        try {
            result = calculatorFunction.calculateFunctionSum(1, upperBound);
            LOGGER.info("Результат функции класса - " + functions.getClass().getCanonicalName() + " - " + result);
        }catch (IllegalStateException e){
            LOGGER.error("task interrupt", e);
        }catch (Exception e){
            LOGGER.error("Unknown error", e);
        }
    }
}
