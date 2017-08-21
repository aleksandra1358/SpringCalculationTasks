package com.globallogic.ultimateCalculationTool.taskService;

import com.globallogic.ultimateCalculationTool.task.ConcreteTask;
import com.globallogic.ultimateCalculationTool.task.ConcreteTaskRepository;
import com.globallogic.ultimateCalculationTool.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcreteTaskService implements TaskService
{
    private final ConcreteTaskRepository repository;

    @Autowired
    public ConcreteTaskService(ConcreteTaskRepository repository) {this.repository = repository;}

    @Override
    public Task createTask(String description)
    {
        return repository.save(new ConcreteTask(description));
    }

    @Override
    public Task getTaskById(final Long id)
    {
        return repository.findById(id);
    }
}

