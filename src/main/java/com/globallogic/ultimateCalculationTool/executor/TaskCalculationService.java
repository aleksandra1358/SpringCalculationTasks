package com.globallogic.ultimateCalculationTool.executor;

import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.task.Task;

import java.util.Optional;

public interface TaskCalculationService
{
    Result executeTask(Task task);

    Optional<Result> findResult(Task task);
}
