package com.intuit.driverService.common.enums;


public enum DateFormats {


    DD_MM_YYYY("dd/MM/yyyy"),
    DD_mm_YYYY("dd-MM-yyyy"),
    DD_mm("dd-MM"),
    MM_yyyy("MM-yyyy"),
    MMMM_DD_YYYY("MMMM dd, yyyy");

    private String dateFormat;


    DateFormats(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
