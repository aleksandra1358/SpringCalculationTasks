package com.globallogic.ultimateCalculationTool.taskData;

import com.globallogic.ultimateCalculationTool.Operation;

import java.util.List;

public interface TaskData
{
    long getId();
    List<Double> getValues();
    Operation getOperation();
}
