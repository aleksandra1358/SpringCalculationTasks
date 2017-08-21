package com.globallogic.ultimateCalculationTool.task;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConcreteTaskRepository extends CrudRepository<ConcreteTask, Long>
{
    List<ConcreteTask> findAll();
    Task findById(final Long id);
}
