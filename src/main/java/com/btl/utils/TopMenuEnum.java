package com.btl.utils;

public enum TopMenuEnum {
    INSURANCE_FEES("דמי ביטוח"),
    RIGHTS_USAGE("מיצוי זכויות"),
    BENEFITS_ALLOWANCES("קצבאות והטבות"),
    CONTACT_US("יצירת קשר");

    private final String displayText;

    TopMenuEnum(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
