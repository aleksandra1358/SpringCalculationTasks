package com.globallogic.ultimateCalculationTool.taskData;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.task.ConcreteTask;
import com.globallogic.ultimateCalculationTool.task.Task;

import javax.persistence.*;
import java.util.List;

@Entity
public class ConcreteTaskData implements TaskData
{
    @Id
    @Column(name = "data_id")
    private Long id;

//todo    private List<Double> values;

    private Operation operation;

    @OneToOne(targetEntity = ConcreteTask.class, fetch = FetchType.LAZY, mappedBy = "data")
    @MapsId
    @JoinColumn(name = "data_id")
    private Task task;

    public ConcreteTaskData()
    {
    }

    public ConcreteTaskData(Operation operation, Task task)
    {
        this.operation = operation;
        this.task = task;
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public List<Double> getValues()
    {
        return null;
    }

    @Override
    public Operation getOperation()
    {
        return null;
    }

    public Task getTask()
    {
        return task;
    }
}
