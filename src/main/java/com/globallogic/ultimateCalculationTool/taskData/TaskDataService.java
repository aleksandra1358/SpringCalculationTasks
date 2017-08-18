package com.globallogic.ultimateCalculationTool.taskData;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.task.Task;

import java.util.List;

public interface TaskDataService
{
    TaskData createTaskData(List<Double> values, Operation operation, Task task);
}
