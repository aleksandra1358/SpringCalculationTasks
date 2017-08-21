package com.globallogic.ultimateCalculationTool.task;

import com.globallogic.ultimateCalculationTool.executor.TaskCalculationServiceDBImpl;
import com.globallogic.ultimateCalculationTool.result.ResultDBImpl;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataDBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    TaskDBImpl getTask(@PathVariable Long id)
    {
        return (TaskDBImpl) repository.findById(id);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    TaskDBImpl createTask(@RequestBody String description)
    {
        return repository.save(new TaskDBImpl(description));
    }


    //todo
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    List<ResultDBImpl> getResults()
    {
        List<ResultDBImpl> results = new ArrayList<>();
        repository.findAll().forEach(t -> results.add((ResultDBImpl) t.getResult()));
        return results;
    }

    //todo
    @RequestMapping(value = "/results/{id}", method = RequestMethod.GET)
    ResultDBImpl getResult(@PathVariable Long id)
    {
        return (ResultDBImpl) repository.findById(id).getResult();
    }

    @RequestMapping(value = "/set/{id}", method = RequestMethod.POST)
    TaskDBImpl setData(@PathVariable Long id, @RequestBody TaskDataDBImpl data)
    {
        TaskDBImpl task = (TaskDBImpl) repository.findById(id);
        task.setData(data);
        data.setTask(task);
        return repository.save(task);
    }

    @RequestMapping(value = "/exec/{id}", method = RequestMethod.POST)
    TaskDBImpl executeOne(@PathVariable Long id)
    {
        TaskDBImpl task = (TaskDBImpl) repository.findById(id);
        TaskCalculationServiceDBImpl calculationService = new TaskCalculationServiceDBImpl(repository);
        calculationService.executeTask(task);
        if(calculationService.findResult(task).isPresent())
        {
            ResultDBImpl result = (ResultDBImpl) calculationService.findResult(task).get();
            result.setTask(task);
        }
        return repository.save(task);
    }

    @RequestMapping(value = "/exec", method = RequestMethod.POST)
    List<TaskDBImpl> executeAll()
    {
        List<TaskDBImpl> tasks = repository.findAll();
        TaskCalculationServiceDBImpl calculationService = new TaskCalculationServiceDBImpl(repository);
        tasks.forEach(t -> exec(calculationService, t));
        return tasks;
    }

    private void exec(TaskCalculationServiceDBImpl calculationService, Task task)
    {
        if(task.getData() == null) return;
        calculationService.executeTask(task);
        if(calculationService.findResult(task).isPresent())
        {
            ResultDBImpl result = (ResultDBImpl) calculationService.findResult(task).get();
            result.setTask(task);
        }
    }
}
