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
        CalculatorFunction calculatorFunction = new CalculatorFunction();
        Functions functions = new SumOneDivideNxNPlusOne();
        ConsoleReader consoleReader = new ConsoleReader();

        calculatorFunction.setFunctions(functions);

        long upperBound;
        double result;
        upperBound = consoleReader.consoleReadNumber();
        try {
            result = calculatorFunction.calculateFunctionSum(1, upperBound);
            System.out.print("Результат функции класса - " + functions.getClass().getCanonicalName() + " - ");
            System.out.println(result);
        }catch (IllegalStateException e){
            LOGGER.error("task interrupt", e);
        }catch (Exception e){
            LOGGER.error("Unknown error", e);
        }
    }
}
