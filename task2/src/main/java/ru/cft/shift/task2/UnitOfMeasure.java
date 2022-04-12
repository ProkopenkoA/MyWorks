package ru.cft.shift.task2;

public enum UnitOfMeasure {
    CENTIMETER("см"),
    METER("м"),
    DECIMETER("дм");

    private final String measure;

    UnitOfMeasure(String measure) {
        this.measure = measure;
    }

    public String getMeasure() {
        return measure;
    }

    public static UnitOfMeasure findByText(String text) {
        for (UnitOfMeasure unit : UnitOfMeasure.values()) {
            if (unit.measure.equalsIgnoreCase(text)) {
                return unit;
            }
        }
        return null;
    }
}
