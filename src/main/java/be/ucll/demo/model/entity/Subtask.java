package be.ucll.demo.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Subtask {
    @NotNull
    @NotEmpty
    private String title;
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    //private static int index;
    @ManyToOne
    private Task parentTask;

    public Subtask(String title, String description) {
        //setId();
        this.title = title;
        this.description = description;
    }

    protected Subtask(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        //++index;

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

    //Todo: due date
    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, title='%s', description='%s']",
                id, title, description);
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

}
