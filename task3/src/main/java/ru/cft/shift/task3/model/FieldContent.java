package ru.cft.shift.task3.model;

public enum FieldContent {
    CLOSED("X"),
    EMPTY(" "),
    BOMB("*"),
    MARKED("F"),
    NUM_1("1"),
    NUM_2("2"),
    NUM_3("3"),
    NUM_4("4"),
    NUM_5("5"),
    NUM_6("6"),
    NUM_7("7"),
    NUM_8("8");

    private final String content;

    FieldContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
