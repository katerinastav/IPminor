package be.ucll.demo.model.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class SubtaskDTO {
    @NotEmpty
    private String title;
    private Long id;
    @NotEmpty
    private String description;
    private TaskDTO parentTask;

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

    public TaskDTO getParentTask() {
        return parentTask;
    }

    public void setParentTask(TaskDTO parentTask) {
        this.parentTask = parentTask;
    }
}
