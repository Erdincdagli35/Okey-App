package com.edsoft.okey_app.manager.indicator;

public class IndicatorPrint {

    private IndicatorManager indicatorManager;

    public IndicatorPrint(IndicatorManager indicatorManager) {
        this.indicatorManager = indicatorManager;
    }

    public void print(IndicatorResult result) {
        System.out.println("Indicator : " + result.getIndicator().getColor() + " -> " + result.getIndicator().getNumber());
        System.out.println("Okey     : " + result.getOkey().getColor() + " -> " + result.getOkey().getNumber());
    }
}
