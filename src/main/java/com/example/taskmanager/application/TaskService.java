package com.example.taskmanager.application;

import com.example.taskmanager.application.response.TaskDetails;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDetails> getTasks();

    List<TaskDetails> getSortedTasks(String filterName);

    TaskDetails getTaskDetails(UUID id);

    void updateTask(TaskDetails taskDetails);

    void createTask(TaskDetails taskDetails);

    void deleteTask(UUID id);

    void finishTask(UUID id);
}
