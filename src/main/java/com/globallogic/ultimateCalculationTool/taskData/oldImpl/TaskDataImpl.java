package com.globallogic.ultimateCalculationTool.taskData.oldImpl;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;

import java.util.List;

public class TaskDataImpl implements TaskData
{
    private long id;
    private List<Double> numbers;
    private Operation operation;

    public TaskDataImpl(long id, List<Double> numbers, Operation operation)
    {
        this.id = id;
        this.numbers = numbers;
        this.operation = operation;
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public List<Double> getValues()
    {
        return numbers;
    }

    @Override
    public Operation getOperation()
    {
        return operation;
    }
}
