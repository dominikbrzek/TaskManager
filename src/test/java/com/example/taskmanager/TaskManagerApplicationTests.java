package com.example.taskmanager;

import com.example.taskmanager.adapter.repository.TaskEntity;
import com.example.taskmanager.adapter.repository.TaskRepository;
import com.example.taskmanager.adapter.repository.TaskStatus;
import com.example.taskmanager.application.TaskController;
import com.example.taskmanager.application.response.TaskDetails;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:createData.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:dropData.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TaskManagerApplicationTests {

    @Autowired
    private TaskController taskController;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void contextLoads() {
    }

    private static final TaskDetails DEFAULT_DETAILS =
            new TaskDetails(UUID.fromString("d19578a6-5160-11ee-be56-0242ac120002"),
                    "oldTitle",
                    "oldDescription",
                    LocalDateTime.parse("2023-01-02T00:00:00"),
                    TaskStatus.OPEN);

    @Test
    public void getAllTasks() {
        var response = taskController.getTasks();
        assert response.hasBody();
        assert response.getBody().size() == 1;
        assertTaskDetails(response.getBody().get(0), DEFAULT_DETAILS);
    }

    @Test
    public void createNewTask() {
        assert taskRepository.findAll().size() == 1;
        TaskDetails newTaskDetails = new TaskDetails();
        newTaskDetails.setDeadline(LocalDateTime.of(2023,1,1,1,1));
        newTaskDetails.setTitle("title");
        newTaskDetails.setDescription("description");
        newTaskDetails.setStatus(TaskStatus.OPEN);
        taskController.createTask(newTaskDetails);
        assert taskRepository.findAll().size() == 2;
    }

    @Test
    public void updateTask() {
        assert taskRepository.findAll().size() == 1;
        TaskDetails newTaskDetails = new TaskDetails();
        newTaskDetails.setId(DEFAULT_DETAILS.getId());
        newTaskDetails.setDeadline(LocalDateTime.of(2023,1,1,1,1));
        newTaskDetails.setTitle("title");
        newTaskDetails.setDescription("description");
        newTaskDetails.setStatus(TaskStatus.OPEN);
        taskController.updateTask(newTaskDetails);
        assert taskRepository.findAll().size() == 1;

        var taskAfter = taskController.getTaskDetails(DEFAULT_DETAILS.getId()).getBody();
        assert taskAfter != null;
        assertTaskDetails(taskAfter, newTaskDetails);
    }

    @Test
    public void deleteTask() {
        assert taskRepository.findById(DEFAULT_DETAILS.getId()).isPresent();
        taskController.deleteTask(DEFAULT_DETAILS.getId());
        assert taskRepository.findById(DEFAULT_DETAILS.getId()).isEmpty();
    }

    private void assertTaskDetails(TaskDetails t1, TaskDetails t2) {
        assert Objects.equals(t1.getId(), t2.getId());
        assert Objects.equals(t1.getTitle(), t2.getTitle());
        assert Objects.equals(t1.getDeadline(), t2.getDeadline());
        assert Objects.equals(t1.getDescription(), t2.getDescription());
        assert Objects.equals(t1.getStatus(), t2.getStatus());
    }

    private void assertTasks(TaskDetails t1, TaskEntity t2) {
        assert Objects.equals(t1.getTitle(), t2.getTitle());
        assert Objects.equals(t1.getDeadline(), t2.getDeadline());
        assert Objects.equals(t1.getDescription(), t2.getDescription());
        assert Objects.equals(t1.getStatus(), t2.getStatus());
    }

}
