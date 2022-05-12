package ru.cft.shift.task3.model;

import java.util.stream.Stream;

public class Field {
    private FieldContent fieldContent;
    private boolean open = false;
    private boolean flag = false;

    public String getContent() {
        return fieldContent.getContent();
    }

    public FieldContent getFieldContent() {
        return fieldContent;
    }

    public void setFieldContent(String fieldContent) {
        this.fieldContent = Stream.of(FieldContent.values()).filter(v -> v.getContent().equals(fieldContent)).findAny().get();
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
