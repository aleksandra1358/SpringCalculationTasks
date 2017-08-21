package com.globallogic.ultimateCalculationTool.executor;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.result.ResultDBImpl;
import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.task.TaskDBImpl;
import com.globallogic.ultimateCalculationTool.task.TaskDBImplRepository;
import com.globallogic.ultimateCalculationTool.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCalculationServiceDBImpl implements TaskCalculationService
{
    private final TaskDBImplRepository repository;

    @Autowired
    public TaskCalculationServiceDBImpl(final TaskDBImplRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Result executeTask(Task task)
    {
        ResultDBImpl result = new ResultDBImpl();
        result.setResult(0d);
        List<Double> values = task.getData().getValues();
        Operation operation = task.getData().getOperation();
        switch(operation)
        {
            case plus:
                values.forEach(v -> result.setResult(result.getResult() + v));
                break;
        }
        task.setResult(result);
        result.setTask(task);
        repository.save((TaskDBImpl) task);
        return result;
    }

    @Override//?
    public Optional<Result> findResult(Task task)
    {
        return Optional.ofNullable(task.getResult());
    }
}
