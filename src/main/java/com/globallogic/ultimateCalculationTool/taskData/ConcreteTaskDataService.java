package com.globallogic.ultimateCalculationTool.taskData;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcreteTaskDataService implements TaskDataService
{
    private final ConcreteTaskDataRepository repository;

    @Autowired
    public ConcreteTaskDataService(ConcreteTaskDataRepository repository) {this.repository = repository;}

    @Override
    public TaskData createTaskData(List<Double> values, Operation operation, Task task)
    {
        return repository.save(new ConcreteTaskData(operation, task));
    }
}
