package ru.cft.shift.task1;

public class MultiplicationTables {
    Integer size;
    Integer indent;//отступ, кол-во разрядов в числе
    Integer indentMaxIndex;

    public MultiplicationTables(Integer size) {
        this.size = size;
        this.indent = digitsOfNumber((long) size *size);
        this.indentMaxIndex = digitsOfNumber(size);
    }

    static int digitsOfNumber(long num) {
        long x = 10;
        for (int i=1; i<19; i++) {
            if (num < x)
                return i;
            x = 10*x;
        }
        return 19;
    }

    private void printLineTable(){
        System.out.print("\n");

        for(int i = indentMaxIndex; i!=0; i--){
            System.out.print("-");
        }
        System.out.print("+");

        for(int index = size; index > 0; index--){
            for(int rank = indent; rank > 0; rank--){
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.print("\n");
    }

    private void printFirstNumber(long number){
        for (int numIndent = digitsOfNumber(number); numIndent != indentMaxIndex; numIndent++){
            System.out.print(" ");
        }
        System.out.print(number + "|");
    }

    private void printFirstLine(){
        for(int i = indentMaxIndex; i!=0; i--){
            System.out.print(" ");
        }
        System.out.print("|");

        int numIndent;
        for(int index = 1; index <= size; index++){
            for(numIndent = indent - digitsOfNumber(index); numIndent != 0; numIndent --){
                System.out.print(" ");
            }
            System.out.print(index + "|");
        }
        printLineTable();
    }

    public void printTable(){
        printFirstLine();

        long number;
        int numIndent;

         for(long index1 = 1; index1 <= size; index1++){
             printFirstNumber(index1);
             for(long index2 = 1; index2 <= size; index2++){
                 number = index1*index2;
                 for(numIndent = indent - digitsOfNumber(number); numIndent != 0; numIndent --){
                     System.out.print(" ");
                 }
                 System.out.print(number + "|");
             }
             printLineTable();
         }
    }
}
