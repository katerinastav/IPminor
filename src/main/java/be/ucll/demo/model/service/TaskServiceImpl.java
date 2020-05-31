package be.ucll.demo.model.service;

import be.ucll.demo.model.dto.SubtaskDTO;
import be.ucll.demo.model.entity.Subtask;
import be.ucll.demo.model.entity.Task;
import be.ucll.demo.model.dto.TaskDTO;
import be.ucll.demo.model.repo.SubtaskRepository;
import be.ucll.demo.model.repo.TaskRepository;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements be.ucll.demo.model.service.TaskService {
	private final TaskRepository repository;
	private final SubtaskRepository subtaskRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository repository, SubtaskRepository subRepository) {
		this.repository = repository;
		this.subtaskRepository = subRepository;
	}

	@Override
	public List<TaskDTO> getTasks() {
		return repository.findAll().stream().map(this::convert).collect(Collectors.toList());
	}

	@Override
	public void addTask(TaskDTO taskDTO) {
		Task task = new Task(taskDTO.getTitle(), taskDTO.getDueDate(), taskDTO.getDescription());
		repository.save(task);
	}

	@Override
	public Task getTask(Long id){
		/*Task tasktemp= null;
		for (Task task : repository.getTasks()){
			if (task.getId() == id) tasktemp = task;
		}
		return tasktemp;*/
		return repository.getOne(id);
	}

	@Override
	public void editTask(Long id, TaskDTO taskDTO) {
		Task task = this.repository.findById(id).get();
		task.setTitle(taskDTO.getTitle());
		task.setDueDate(taskDTO.getDueDate());
		task.setDescription(taskDTO.getDescription());
		repository.saveAndFlush(task);
	}

	@Override
	public void addSubtask(SubtaskDTO subtaskDTO) {
		Subtask subtask = new Subtask(subtaskDTO.getTitle(), subtaskDTO.getDescription());
		Task task = this.repository.findById(subtaskDTO.getId()).get();
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(task.getId());
		taskDTO.setTitle(task.getTitle());
		taskDTO.setDueDate(task.getDueDate());
		taskDTO.setDescription(task.getDescription());
		//taskDTO.setSubtaskDTOS(task.getSubtasks());
		List<Subtask> subtasks = task.getSubtasks();
		List<SubtaskDTO> subtaskDTOS = new ArrayList<>();
		for (Subtask t : subtasks){
			SubtaskDTO dto = new SubtaskDTO();
			dto.setId(t.getId());
			dto.setTitle(t.getTitle());
			dto.setDescription(t.getDescription());
			dto.setParentTask(taskDTO);
			subtaskDTOS.add(dto);
		}
		subtaskDTOS.add(subtaskDTO);
		subtaskDTO.setParentTask(taskDTO);
		subtask.setParentTask(task);
		taskDTO.setSubtaskDTOS(subtaskDTOS);
		this.repository.saveAndFlush(task);
		this.subtaskRepository.save(subtask);
	}

	private TaskDTO convert(Task t){
		TaskDTO dto = new TaskDTO();
		dto.setTitle(t.getTitle());
		dto.setDueDate(t.getDueDate());
		dto.setDescription(t.getDescription());
		dto.setId(t.getId());
		List<Subtask> subtasks = t.getSubtasks();
		List<SubtaskDTO> subtaskDTOS = new ArrayList<>();
		for (Subtask subtask : subtasks){
			SubtaskDTO subtaskDTO = new SubtaskDTO();
			//subtaskDTO.set
			subtaskDTOS.add(subtaskDTO);
		}
		dto.setSubtaskDTOS(subtaskDTOS);
		return dto;
	}
}
