package com.example.taskmanager.adapter;

import com.example.taskmanager.adapter.repository.TaskEntity;
import com.example.taskmanager.adapter.repository.TaskRepository;
import com.example.taskmanager.adapter.repository.TaskStatus;
import com.example.taskmanager.application.TaskService;
import com.example.taskmanager.application.response.TaskDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.taskmanager.adapter.StreamUtils.toStreamSafe;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Override
    public List<TaskDetails> getTasks() {
        log.info("Get all tasks");
        return toStreamSafe(repository.findAll())
                .map(this::mapFromEntity)
                .toList();
    }

    @Override
    public List<TaskDetails> getSortedTasks(String filterName) {
        return toStreamSafe(repository.findAll(Sort.by(Sort.Direction.ASC, filterName)))
                .map(this::mapFromEntity)
                .toList();
    }

    @Override
    public TaskDetails getTaskDetails(UUID id) {
        log.info("Get task details by id: {}", id);
        return repository.findById(id)
                .map(this::mapFromEntity)
                .orElseThrow();
    }

    @Override
    public void updateTask(TaskDetails taskDetails) {
        log.info("Update task with id: {}", taskDetails.getId());
        var entity = repository.findById(taskDetails.getId()).orElseThrow();
        updateEntityAndSave(taskDetails, entity);
    }

    @Override
    public void createTask(TaskDetails taskDetails) {
        log.info("Create new task");
        var entity = new TaskEntity();
        updateEntityAndSave(taskDetails, entity);
    }

    @Override
    public void deleteTask(UUID id) {
        log.info("Delete task by id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public void finishTask(UUID id) {
        log.info("Finish task with id: {}", id);
        var entity = repository.findById(id).orElseThrow();
        entity.setStatus(TaskStatus.FINISHED);
        repository.save(entity);
    }

    private TaskDetails mapFromEntity(TaskEntity entity) {
        return TaskDetails.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .status(entity.getStatus())
                .build();
    }

    private void updateEntityAndSave(TaskDetails details, TaskEntity entity) {
        entity.setTitle(details.getTitle());
        entity.setDescription(details.getDescription());
        entity.setDeadline(details.getDeadline());
        entity.setStatus(details.getStatus());
        repository.save(entity);
    }
}
