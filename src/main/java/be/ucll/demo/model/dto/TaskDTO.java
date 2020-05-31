package be.ucll.demo.model.dto;

import jdk.dynalink.linker.LinkerServices;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class TaskDTO {
	@NotEmpty
	private String title;
	@NotEmpty
	private LocalDateTime dueDate;
	private Long id;
	@NotEmpty
	private String description;
	private List<SubtaskDTO> subtaskDTOS;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public List<SubtaskDTO> getSubtaskDTOS() {
		return subtaskDTOS;
	}

	public void setSubtaskDTOS(List<SubtaskDTO> subtaskDTOS) {
		this.subtaskDTOS = subtaskDTOS;
	}
}
