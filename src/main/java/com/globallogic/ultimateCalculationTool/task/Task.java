package com.globallogic.ultimateCalculationTool.task;

import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;

public interface Task
{
    Long getId();
    String getDescription();
    Result getResult();
    TaskData getData();
    void setData(final TaskData data);
    void setResult(Result result);
}
