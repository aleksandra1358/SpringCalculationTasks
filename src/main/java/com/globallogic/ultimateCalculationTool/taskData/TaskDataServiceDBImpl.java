package com.globallogic.ultimateCalculationTool.taskData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.task.TaskDBImpl;
import com.globallogic.ultimateCalculationTool.task.TaskDBImplRepository;

@Service
public class TaskDataServiceDBImpl implements TaskDataService {
	private final TaskDBImplRepository repository;

	@Autowired
	public TaskDataServiceDBImpl(final TaskDBImplRepository repository) {
		this.repository = repository;
	}

	@Override
	public TaskData createTaskData(List<Double> values, Operation operation, Task task) {
		TaskDataDBImpl data = new TaskDataDBImpl(operation, values);
		task.setData(data);
		data.setTask(task);
		repository.save((TaskDBImpl) task);
		return data;
	}
}
