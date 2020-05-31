package be.ucll.demo.controller;

import be.ucll.demo.model.dto.SubtaskDTO;
import be.ucll.demo.model.dto.TaskDTO;
import be.ucll.demo.model.entity.Subtask;
import be.ucll.demo.model.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;


@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    //@Autowired
    //public TaskController(TaskService taskService){
      //  this.taskService = taskService;
  //  }

    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping
    public String getTasks(Model model){
        if (taskService.getTasks().isEmpty()) return "notasks";
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks";
    }
/*
    @PostMapping
    public String addTask(@ModelAttribute TaskDTO taskDTO){
        taskService.addTask(taskDTO);
        return "redirect:/tasks";
    }*/

    //Todo met @RequestMapping?
    //moet het dto of task zijn?
    @RequestMapping("/{id}")
    @GetMapping
    public String getDetails(@PathVariable("id") Long id, Model model) {
        for (TaskDTO task : taskService.getTasks()){
            if (task.getId().equals(id)){
                model.addAttribute("task", taskService.getTask(id));
                return "details";
            }
        }
        return "redirect:/tasks/notfound";
    }

    @GetMapping("/notfound")
    public String notFound(){
        return "notfound";
    }

    @GetMapping("/new")
    public String getCreateForm(Model model){
        model.addAttribute("task", new TaskDTO());
        return "addtask";
    }

    @PostMapping("/new")
    public String postNewTask(@ModelAttribute("task") @Valid TaskDTO task, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "addtask";
        }
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("task", this.taskService.getTask(id));
        this.taskService.getTask(id);
        return "edittask";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@ModelAttribute("task") @Valid TaskDTO task, @PathVariable("id") Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "edittask";
        }
        taskService.editTask(task.getId(), task);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("/{id}/sub/create")
    public String getSubForm(Model model, @PathVariable("id") String id){
        model.addAttribute("id", id);
        model.addAttribute("subtask", new SubtaskDTO());
        return "addsubtask";
    }

    @PostMapping("/{id}/sub/create")
    public String postNewSubtask(@ModelAttribute("subtask") @Valid SubtaskDTO subtask, BindingResult bindingResult, @PathVariable String id){
        if (bindingResult.hasErrors()){
            return "addsubtask";
        }
        taskService.addSubtask(subtask);

        return "redirect:/tasks/"+id;
    }
/*
    or (TaskDTO task : taskService.getTasks()){
        if (task.getId().equals(id)){
            model.addAttribute("task", taskService.getTask(id));
            return "details";
        }
*/


}
