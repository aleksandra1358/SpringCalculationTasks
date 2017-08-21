package com.globallogic.ultimateCalculationTool.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskDBImplController
{
    private final TaskDBImplRepository repository;

    @Autowired
    public TaskDBImplController(TaskDBImplRepository repository)
    {
        this.repository = repository;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    List<TaskDBImpl> getTasks()
    {
        return repository.findAll();
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    TaskDBImpl createTask(@RequestBody String description)
    {
        return repository.save(new TaskDBImpl(description));
    }
}
