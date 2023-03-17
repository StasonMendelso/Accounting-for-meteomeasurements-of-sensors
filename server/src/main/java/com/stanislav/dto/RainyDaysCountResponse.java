package com.stanislav.dto;

/**
 * @author Stanislav Hlova
 */
public class RainyDaysCountResponse {
    private long rainyDaysCount;

    public RainyDaysCountResponse(long rainyDaysCount) {
        this.rainyDaysCount = rainyDaysCount;
    }

    public long getRainyDaysCount() {
        return rainyDaysCount;
    }

    public void setRainyDaysCount(long rainyDaysCount) {
        this.rainyDaysCount = rainyDaysCount;
    }
}
