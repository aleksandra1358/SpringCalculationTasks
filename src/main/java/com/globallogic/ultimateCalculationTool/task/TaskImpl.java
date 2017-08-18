package com.globallogic.ultimateCalculationTool.task;

import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;

public class TaskImpl implements Task
{
    private long id;
    private String description;
    private Result result;
    private TaskData data;

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public Result getResult()
    {
        return result;
    }

    @Override
    public TaskData getData()
    {
        return data;
    }

    public void setResult(Result result)
    {
        this.result = result;
    }

    public void setData(TaskData data)
    {
        this.data = data;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }
}
