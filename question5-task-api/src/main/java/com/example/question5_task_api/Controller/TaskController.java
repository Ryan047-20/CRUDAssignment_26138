package com.example.question5_task_api.Controller;

import com.example.question5_task_api.model.Task;
import com.example.question5_task_api.repository.taskrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private taskrepo taskRepository;

    // GET all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    // GET task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return task.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // GET tasks by completion status
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getByStatus(@RequestParam boolean completed) {
        return ResponseEntity.ok(taskRepository.findByCompleted(completed));
    }

    // GET tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getByPriority(@PathVariable String priority) {
        return ResponseEntity.ok(taskRepository.findByPriorityIgnoreCase(priority));
    }

    // POST create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task saved = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT update task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Optional<Task> existing = taskRepository.findById(taskId);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Task task = existing.get();
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());
        return ResponseEntity.ok(taskRepository.save(task));
    }

    // PATCH mark task as completed
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markAsCompleted(@PathVariable Long taskId) {
        Optional<Task> existing = taskRepository.findById(taskId);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Task task = existing.get();
        task.setCompleted(true);
        return ResponseEntity.ok(taskRepository.save(task));
    }

    // DELETE task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(taskId);
        return ResponseEntity.noContent().build();
    }
}