package ru.cft.shift.task3.model;

import java.util.stream.Stream;

public class Cell {
    private CellContent cellContent;
    private boolean open = false;
    private boolean flag = false;

    public String getContent() {
        return cellContent.getContent();
    }

    public CellContent getCellContent() {
        return cellContent;
    }

    public void setCellContent(String cellContent) {
        this.cellContent = Stream.of(CellContent.values()).filter(v -> v.getContent().equals(cellContent)).findAny().get();
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {

        this.open = open;
    }

    public boolean isFlag() {
        return flag;
    }

    public void changeFlag() {
        this.flag = !flag;
    }
}
