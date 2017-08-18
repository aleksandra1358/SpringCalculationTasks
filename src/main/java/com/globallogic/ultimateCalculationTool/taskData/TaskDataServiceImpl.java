package com.globallogic.ultimateCalculationTool.taskData;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.task.Task;

import java.util.List;

public class TaskDataServiceImpl implements TaskDataService
{
    @Override
    public TaskData createTaskData(List<Double> values, Operation operation, Task task)
    {
        TaskDataImpl data = new TaskDataImpl(1, values, operation);
        task.setData(data);
        return data;
    }
}
