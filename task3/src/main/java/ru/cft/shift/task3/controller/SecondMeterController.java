package ru.cft.shift.task3.controller;

import ru.cft.shift.task3.model.secondmeter.SecondMeterInterface;
import ru.cft.shift.task3.view.RecordNameListener;

public class SecondMeterController implements RecordNameListener {
    private final SecondMeterInterface secondMeterInterface;

    public SecondMeterController(SecondMeterInterface secondMeterInterface) {
        this.secondMeterInterface = secondMeterInterface;
    }

    public void onRecordNameEntered(String name) {
        if(name.isEmpty()){
            name = "Unknown";
        }
        secondMeterInterface.setHighScoreName(name);
    }
    public void stop(){
        secondMeterInterface.stopSecondMeter();
    }
}
