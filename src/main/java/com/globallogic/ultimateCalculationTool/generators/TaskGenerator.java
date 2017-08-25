package com.globallogic.ultimateCalculationTool.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.taskService.TaskService;

@Component
public class TaskGenerator {

	@Autowired
	private TaskDataGenerator taskDataGenerator;

	@Autowired
	private TaskService taskService;

	public void generateTasks(int ammount) {
		for (int i = 0; i < ammount; i++) {
			generateTask();
		}
	}

	private void generateTask() {
		Task task = taskService.createTask("aaa");
		taskDataGenerator.generateTaskData(task);

	}

}
