package com.globallogic.ultimateCalculationTool.task;

import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;

public interface Task
{
    long getId();
    String getDescription();
    Result getResult();
    TaskData getData();

    //todo ask for data accessing
    void setData(final TaskData data);

    void setResult(Result result);
}