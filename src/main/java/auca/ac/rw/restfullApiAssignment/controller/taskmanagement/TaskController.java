package auca.ac.rw.restfullApiAssignment.controller.taskmanagement;

import auca.ac.rw.restfullApiAssignment.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Buy groceries", "Milk, eggs, bread", false, "MEDIUM", "2026-02-20"));
        tasks.add(new Task(2L, "Study Java", "Finish Spring Boot module", false, "HIGH", "2026-02-18"));
        tasks.add(new Task(3L, "Gym session", "Leg day workout", true, "LOW", "2026-02-15"));
        tasks.add(new Task(4L, "Clean room", "Organize desk and wardrobe", false, "LOW", "2026-02-22"));
        tasks.add(new Task(5L, "Prepare presentation", "Slides for software project", true, "HIGH", "2026-02-13"));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getById(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted() == completed) {
                result.add(t);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) {
                result.add(t);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        tasks.add(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task newData) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setTitle(newData.getTitle());
                t.setDescription(newData.getDescription());
                t.setCompleted(newData.isCompleted());
                t.setPriority(newData.getPriority());
                t.setDueDate(newData.getDueDate());
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setCompleted(true);
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        Task target = null;
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                target = t;
                break;
            }
        }
        if (target != null) {
            tasks.remove(target);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
