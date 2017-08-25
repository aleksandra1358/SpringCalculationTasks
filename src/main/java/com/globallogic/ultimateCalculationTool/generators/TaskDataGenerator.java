package com.globallogic.ultimateCalculationTool.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataService;

@Component
public class TaskDataGenerator {

	@Autowired
	private TaskDataService taskDataService;

	private Random generator;

	public TaskDataGenerator() {
		generator = new Random();
	}

	public TaskData generateTaskData(Task task) {
		return taskDataService.createTaskData(generateValues(), generateOperation(), task);

	}

	private List<Double> generateValues() {
		List<Double> values = new ArrayList<>();
		for (int i = 0; i < 2 + generator.nextInt(8); i++) {
			values.add((double) generator.nextInt(101));
		}
		return values;
	}

	private Operation generateOperation() {
		int o = generator.nextInt(3);
		for (Operation operation : Operation.values()) {
			if (operation.ordinal() == o) {
				return operation;
			}
		}
		return null;

	}

}
