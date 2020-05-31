package be.ucll.demo.model.repo;

import be.ucll.demo.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{
   /* Task findById(long id);
    List<Task> findByDueDate(LocalDateTime dueDate);*/
}
