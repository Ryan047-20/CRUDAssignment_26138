package com.example.question5_task_api.repository;

import com.example.question5_task_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface taskrepo extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
    List<Task> findByPriorityIgnoreCase(String priority);

}
