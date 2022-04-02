package ru.cft.shift.task2;


import java.util.logging.Logger;

public class TryArguments {


    private static final Logger log = Logger.getLogger(Main.class.getName());

    char output = 0;
    String outPutFileName;
    double[] segment = new double[3];
    char type = 0;

    public TryArguments(String[] args) throws Exception {
        log.fine("Создан TryArguments");
        int maxIndex = args.length;
        if (maxIndex != 2 && maxIndex != 3) {
            throw new MyException("Ошибка входных данных: кол-во параметров задано не верно");
        }
        log.fine("Задано правильное кол-во параметров");
        int index = 0;
        while (index != maxIndex) {
            args[index] = checkParamTilda(args[index]);
            if (args[index].equals("c") && this.output == 0) {
                this.output = 'c';
                index++;
                continue;
            }
            if (args[index].equals("f") && this.output == 0) {
                this.output = 'f';
                index++;
                this.outPutFileName = args[index];
                index++;
                continue;
            }
            if (this.type == 0) {
                log.fine("Создается InputFile");
                InputFileReader inFile = new InputFileReader(args[index]);
                log.fine("Входной файл прошел проверку данных");
                this.segment = inFile.getSegment();
                this.type = inFile.getType();
                index++;
                continue;
            }
            throw new MyException("Ошибка входных данных: параметр задан не верно");
        }

    }

    public char getOutput() {
        return output;
    }

    public String getOutPutFileName() {
        return outPutFileName;
    }

    public double[] getSegment() {
        return segment;
    }

    public char getType() {
        return type;
    }

    private String checkParamTilda(String param) throws MyException {
        if (param.charAt(0) == '-') {
            return param.substring(1);
        } else {
            throw new MyException("Ошибка ввода входных данных: параметр начинается не с '-'");
        }
    }
}
