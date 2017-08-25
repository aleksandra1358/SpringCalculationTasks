package com.globallogic.ultimateCalculationTool.scheduleExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.globallogic.ultimateCalculationTool.executor.TaskCalculationServiceDBImpl;
import com.globallogic.ultimateCalculationTool.task.TaskDBImpl;
import com.globallogic.ultimateCalculationTool.taskService.TaskServiceDBImpl;

@Component
public class ScheduledExecutorService {
	private static final Logger log = LoggerFactory.getLogger(ScheduledExecutorService.class);

	private Map<Long, Boolean> tasks = new HashMap<>();

	@Autowired
	private TaskServiceDBImpl taskService;

	@Autowired
	private TaskCalculationServiceDBImpl calculationService;

	@Async
	@Scheduled(fixedDelayString = "${load.fixedDelay.in.miliseconds}")
	public void loadTasksWithoutResult() {
		List<TaskDBImpl> allTasks = taskService.getAllTasks();
		for (TaskDBImpl task : allTasks) {
			if (task.getData() == null) {

			} else {
				if ((task.getResult() == null || task.getResult().equals(0d))) {
					tasks.put(task.getId(), false);
					log.info("A new to do task: {}", task.getId());
				}
			}
		}

	}

	@Async
	@Scheduled(fixedRateString = "${exec.fixedRate.in.milliseconds}")
	public void executeTask() {
		Long id = taskToDo();

		log.info("The task to do is: {}", id);
		if (id == null) {
			return;
		} else {
			bookTask(id);
			log.info("The task which is booked: {}", id);
			calculationService.executeTask(taskService.getTaskById(id));
			log.info("Executed task is: {} ", id);
			deleteTask(id);
			log.info("The task deleted from to do list: {}", id);
		}

	}

	public synchronized int getAmmountOfTasks() {
		return tasks.size();
	}

	private synchronized Long taskToDo() {
		for (Map.Entry<Long, Boolean> task : tasks.entrySet()) {
			if (task.getValue() != false) {

			} else
				return task.getKey();
		}
		return null;
	}

	private synchronized void bookTask(Long id) {
		tasks.put(id, true);
	}

	private synchronized void deleteTask(Long id) {
		tasks.remove(id);
	}

}
