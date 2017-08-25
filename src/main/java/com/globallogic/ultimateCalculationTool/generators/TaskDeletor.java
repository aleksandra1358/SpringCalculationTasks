package com.globallogic.ultimateCalculationTool.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.globallogic.ultimateCalculationTool.task.TaskDBImplRepository;

@Component
public class TaskDeletor {

	@Autowired
	private TaskDBImplRepository taskRepository;

	public void deleteAllTasks() {
		taskRepository.deleteAll();
	}

}
