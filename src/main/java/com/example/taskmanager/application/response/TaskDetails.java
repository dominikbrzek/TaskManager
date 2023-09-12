package com.example.taskmanager.application.response;

import com.example.taskmanager.adapter.repository.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetails {

    private UUID id;

    private String title;

    private String description;

    private LocalDateTime deadline;

    private TaskStatus status;
}
