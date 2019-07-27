package com.rainbow.statistics.domain;

import java.util.List;

public class PermitReportResult {

    private List<String> yearDate;

    private List<PermitTypeNumber> numberList;

    public List<PermitTypeNumber> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<PermitTypeNumber> numberList) {
        this.numberList = numberList;
    }

    public List<String> getYearDate() {
        return yearDate;
    }

    public void setYearDate(List<String> yearDate) {
        this.yearDate = yearDate;
    }
}


