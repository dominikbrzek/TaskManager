package com.example.taskmanager.application.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TaskDetails {
    private UUID id;

    private String title;

    private String description;

    private LocalDateTime deadline;

    private String status;
}
