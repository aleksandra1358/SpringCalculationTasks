package com.globallogic.ultimateCalculationTool.taskService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.task.TaskDBImpl;
import com.globallogic.ultimateCalculationTool.task.TaskDBImplRepository;

@Service
public class TaskServiceDBImpl implements TaskService {
	private final TaskDBImplRepository repository;

	@Autowired
	public TaskServiceDBImpl(TaskDBImplRepository repository) {
		this.repository = repository;
	}

	@Override
	public Task createTask(String description) {
		return repository.save(new TaskDBImpl(description));
	}

	@Override
	public Task getTaskById(final Long id) {
		return repository.findById(id);
	}

	public List<TaskDBImpl> getAllTasks() {
		return repository.findAll();
	}

}
