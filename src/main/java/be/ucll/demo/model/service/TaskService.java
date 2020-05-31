package be.ucll.demo.model.service;

import be.ucll.demo.model.dto.SubtaskDTO;
import be.ucll.demo.model.entity.Task;
import be.ucll.demo.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {
	public List<TaskDTO> getTasks();

	void addTask(TaskDTO taskDTO);

	Task getTask(Long id);

	void editTask(Long id, TaskDTO task);

	void addSubtask(SubtaskDTO subtask);
}
