package com.globallogic.ultimateCalculationTool.task;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.result.ResultDBImpl;
import com.globallogic.ultimateCalculationTool.taskData.TaskData;
import com.globallogic.ultimateCalculationTool.taskData.TaskDataDBImpl;

@Entity
public class TaskDBImpl implements Task {
	@Id
	@GeneratedValue
	@Column(name = "task_id")
	@Min(1)
	private Long id;

	private String description;

	@JsonManagedReference
	@OneToOne(targetEntity = TaskDataDBImpl.class, mappedBy = "task", cascade = CascadeType.ALL)
	private TaskData data;

	@JsonManagedReference
	@OneToOne(targetEntity = ResultDBImpl.class, mappedBy = "task", cascade = CascadeType.ALL)
	private Result result;

	public TaskDBImpl() {
	}

	public TaskDBImpl(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Result getResult() {
		return result;
	}

	@Override
	public TaskData getData() {
		return data;
	}

	@Override
	public void setData(TaskData data) {
		this.data = data;
	}

	@Override
	public void setResult(Result result) {
		this.result = result;
	}
}
