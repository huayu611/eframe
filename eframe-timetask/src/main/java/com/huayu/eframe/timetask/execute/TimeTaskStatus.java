package com.huayu.eframe.timetask.execute;

/**
 * Created by Leo on 2019/4/15.
 */
public enum TimeTaskStatus
{
    START("0"),
    PROCESS("1"),
    FINISH("2"),
    FAILED("3"),

    ;
    private String statusValue;

    TimeTaskStatus(String value)
    {
        statusValue = value;
    }

    public String getValue() {
        return statusValue;
    }

}
