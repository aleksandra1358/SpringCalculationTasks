package com.globallogic.ultimateCalculationTool.task;


import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.taskData.ConcreteTaskData;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;

import javax.persistence.*;

@Entity
public class ConcreteTask implements Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @OneToOne(targetEntity = ConcreteTaskData.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private TaskData data;

    public ConcreteTask()
    {
    }

    public ConcreteTask(String description)
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
        return null;
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

    }
}
