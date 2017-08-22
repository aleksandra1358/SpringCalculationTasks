package com.globallogic.ultimateCalculationTool.task;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.globallogic.ultimateCalculationTool.result.ResultDBImpl;
import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataDBImpl;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;

import javax.persistence.*;

@Entity
public class TaskDBImpl implements Task
{
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    private String description;

    @JsonManagedReference
    @OneToOne(targetEntity = TaskDataDBImpl.class, mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TaskData data;

    @JsonManagedReference
    @OneToOne(targetEntity = ResultDBImpl.class, mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Result result;


    public TaskDBImpl()
    {
    }

    public TaskDBImpl(String description)
    {
        this.description = description;
    }

    public Long getId()
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

    @Override
    public void setData(TaskData data)
    {
        this.data = data;
    }

    @Override
    public void setResult(Result result)
    {
        this.result = result;
    }
}
