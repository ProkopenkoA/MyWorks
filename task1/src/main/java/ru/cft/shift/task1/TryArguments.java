package ru.cft.shift.task1;

public class TryArguments {
    Integer sizeTable;

    public TryArguments(String[] args) throws MyException {
        if(args.length != 1) {
            throw new MyException("Ошибка ввода входных данных: задан не один параметр");
        }
        if(args[0].charAt(0) == '-'){
            args[0] = args[0].substring(1);
        }
        else {
            throw new MyException("Ошибка ввода входных данных: параметр начинается не с '-'");
        }
        this.sizeTable = Integer.parseInt(args[0]);
        if (sizeTable < 1 || sizeTable > 32){
            throw new MyException("Ошибка ввода входных данных: число задано не верно");
        }
    }

    public Integer getSizeTable() {
        return sizeTable;
    }
}
