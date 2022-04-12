package ru.cft.shift.task2;


import java.util.logging.Logger;

import static ru.cft.shift.task2.Main.outPut;
import static ru.cft.shift.task2.Main.outPutFileName;

public class ArgumentParser {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    private final String[] args;

    public ArgumentParser(String[] args) {
        this.args = args;
    }

    public void checkArgument() throws Exception {
        log.fine("Создан TryArguments");
        ConsoleArguments argument;
        int maxIndex = args.length;
        if (maxIndex != 3 && maxIndex != 4) {
            throw new MyException("Ошибка входных данных: кол-во параметров задано не верно");
        }
        log.fine("Задано правильное кол-во параметров");
        int index = 0;
        while (index != maxIndex) {
            argument = checkParam(args[index]);
            switch (argument) {
                case INPUTFILE -> {
                    index++;
                    log.fine("Создается InputFile");
                    InputFileReader inFile = new InputFileReader(args[index]);
                    inFile.checkFileInput();
                    log.fine("Входной файл прошел проверку данных");
                    index++;
                    continue;
                }
                case CONSOLE -> {
                    outPut = OutPut.CONSOLE;
                    index++;
                    continue;
                }
                case OUTPUTFILE -> {
                    outPut = OutPut.FILE;
                    index++;
                    outPutFileName = args[index];
                    index++;
                    continue;
                }
            }
            throw new MyException("Ошибка входных данных: параметр задан не верно");
        }
    }

    private ConsoleArguments checkParam(String param) throws MyException {
        ConsoleArguments argument;
        if (param.charAt(0) == '-') {
            param = param.substring(1);
            argument = ConsoleArguments.findByText(param);
            if (argument == null) {
                throw new MyException("Ошибка ввода входных данных: параметр не найден");
            }
            return argument;
        }
        throw new MyException("Ошибка ввода входных данных: параметр начинается не с '-'");
    }
}
