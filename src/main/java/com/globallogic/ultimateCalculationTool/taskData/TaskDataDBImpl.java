package com.globallogic.ultimateCalculationTool.taskData;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.task.TaskDBImpl;

@Entity
public class TaskDataDBImpl implements TaskData {
	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<Double> values;

	private Operation operation;

	@JsonBackReference
	@OneToOne(targetEntity = TaskDBImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	private Task task;

	public TaskDataDBImpl() {
	}

	public TaskDataDBImpl(final Operation operation, final List<Double> values) {
		this.operation = operation;
		this.values = values;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public List<Double> getValues() {
		return values;
	}

	@Override
	public Operation getOperation() {
		return operation;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
