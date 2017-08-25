package com.globallogic.ultimateCalculationTool.executor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.ultimateCalculationTool.Operation;
import com.globallogic.ultimateCalculationTool.result.Result;
import com.globallogic.ultimateCalculationTool.result.ResultDBImpl;
import com.globallogic.ultimateCalculationTool.task.Task;
import com.globallogic.ultimateCalculationTool.task.TaskDBImpl;
import com.globallogic.ultimateCalculationTool.task.TaskDBImplRepository;

@Service
public class TaskCalculationServiceDBImpl implements TaskCalculationService {
	private final TaskDBImplRepository repository;

	// @Autowired
	// private ResultDBImplRepository resultRepository;

	@Autowired
	public TaskCalculationServiceDBImpl(final TaskDBImplRepository repository) {
		this.repository = repository;
	}

	@Override
	public Result executeTask(Task task) {
		System.out.println("Begining of executeTask()");
		ResultDBImpl result = new ResultDBImpl();
		result.setResult(0d);
		List<Double> values = task.getData().getValues();
		Operation operation = task.getData().getOperation();
		System.out.println("Loading data");
		switch (operation) {
		case plus:
			values.forEach(v -> result.setResult(result.getResult() + v));
			break;
		case minus:
			result.setResult(values.get(0));
			for (int i = 1; i < values.size(); i++) {
				result.setResult(result.getResult() - values.get(i));
			}
			break;
		case multiply:
			result.setResult(values.get(0));
			for (int i = 1; i < values.size(); i++) {
				result.setResult(result.getResult() * values.get(i));
			}
			break;
		case divide:
			result.setResult(values.get(0));
			for (int i = 1; i < values.size(); i++) {
				result.setResult(result.getResult() / values.get(i));
			}
			break;
		}
		System.out.println("After switch");

		task.setResult(result);
		System.out.println("After setting result");
		result.setTask(task);
		System.out.println("After setting task");
		System.out.println("Result: " + result.getResult());
		// resultRepository.save(result);
		// System.out.println("After saving result");
		repository.save((TaskDBImpl) task);
		System.out.println("After saving task");
		return result;
	}

	@Override
	public Optional<Result> findResult(Task task) {
		return Optional.ofNullable(task.getResult());
	}
}
