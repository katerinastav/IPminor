package be.ucll.demo.rest.controller;

import be.ucll.demo.model.dto.TaskDTO;
import be.ucll.demo.model.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/task")
    @ResponseBody
    public List<TaskDTO> getTasks(){
        return taskService.getTasks();
    }

    @PostMapping("task")
    public void createNewTask(@RequestBody @Valid TaskDTO taskDTO){
        taskService.addTask(taskDTO);
    }
}
