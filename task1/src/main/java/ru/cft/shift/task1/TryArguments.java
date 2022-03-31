package ru.cft.shift.task1;

public class TryArguments {
    Integer sizeTable;

    public TryArguments(String[] args) throws Exception {
        if(args.length != 2) {
            throw new MyException("Ошибка ввода входных данных: задано неверное кол-во параметров");
        }
        if(args[0].charAt(0) == '-'){
            args[0] = args[0].substring(1);
        }
        else {
            throw new MyException("Ошибка ввода входных данных: параметр начинается не с '-'");
        }
        if (args[0].equals("sizeTable")) {
            this.sizeTable = Integer.parseInt(args[1]);
            if (sizeTable < 1 || sizeTable > 32) {
                throw new MyException("Ошибка ввода входных данных: число задано не верно");
            }
        }
        else throw new MyException("Ошибка ввода входных данных: имя параметра введено не верно");
    }

    public Integer getSizeTable() {
        return sizeTable;
    }
}
