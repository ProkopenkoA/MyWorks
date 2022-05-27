package ru.cft.shift.task3.model;

public enum CellContent {
    EMPTY(" "),
    BOMB("*"),
    NUM_1("1"),
    NUM_2("2"),
    NUM_3("3"),
    NUM_4("4"),
    NUM_5("5"),
    NUM_6("6"),
    NUM_7("7"),
    NUM_8("8"),
    CLOSED,
    MARKED;

    private String content;

    CellContent(String content) {
        this.content = content;
    }

    CellContent() {
    }

    public String getContent() {
        return content;
    }
}
