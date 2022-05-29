package ru.cft.shift.task3.view;

import ru.cft.shift.task3.dto.ButtonType;

public interface CellEventListener {
    void onMouseClick(int x, int y, ButtonType buttonType);
}
