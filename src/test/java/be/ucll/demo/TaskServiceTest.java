package be.ucll.demo;

import be.ucll.demo.model.dto.TaskDTO;
import be.ucll.demo.model.repo.SubtaskRepository;
import be.ucll.demo.model.repo.TaskRepository;
import be.ucll.demo.model.service.TaskService;
import be.ucll.demo.model.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTest {
    private TaskRepository repository;
    private SubtaskRepository subRepository;
    private TaskService taskService = new TaskServiceImpl(repository, subRepository);
    @Test
    public void testGetTasks(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("IP minor.");
        taskDTO.setDescription("FInish PE project for IP minor.");
        taskDTO.setDueDate(LocalDateTime.of(2020, 05, 31, 23, 59));
        taskService.addTask(taskDTO);

        //method to be tested
        List<TaskDTO> tasks = taskService.getTasks();

        //checks
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        TaskDTO task = tasks.get(0);
        assertNotNull(task);
    }
}
