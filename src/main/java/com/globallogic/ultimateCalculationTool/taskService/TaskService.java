package com.globallogic.ultimateCalculationTool.taskService;

import com.globallogic.ultimateCalculationTool.task.Task;

public interface TaskService {
	Task createTask(final String description);

	Task getTaskById(final Long id);
}
