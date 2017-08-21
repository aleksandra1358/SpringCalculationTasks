package com.globallogic.ultimateCalculationTool.task;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskDBImplRepository extends CrudRepository<TaskDBImpl, Long>
{
    List<TaskDBImpl> findAll();
    Task findById(final Long id);
}
