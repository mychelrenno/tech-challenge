package com.fiap.tech_challenge.core.domain.restaurant;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OpeningTime {
    private DayOfWeek dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;

    public OpeningTime(DayOfWeek dayOfWeek,
                       LocalTime openingTime,
                       LocalTime closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }
}
