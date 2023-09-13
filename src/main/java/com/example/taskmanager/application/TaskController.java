package com.example.taskmanager.application;

import com.example.taskmanager.application.response.TaskDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/tasks")
@CrossOrigin("http://localhost:63343/")
public class TaskController {

    private final TaskService service;

    @GetMapping()
    public ResponseEntity<List<TaskDetails>> getTasks() {
        return ResponseEntity.ok(service.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDetails> getTaskDetails(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(service.getTaskDetails(id));
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody TaskDetails request) {
        service.createTask(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Void> updateTask(@RequestBody TaskDetails request) {
        service.updateTask(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") UUID id) {
        service.deleteTask(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> finishTask(@PathVariable(name = "id") UUID id) {
        service.finishTask(id);
        return ResponseEntity.ok(null);
    }
}
