# Task Manager

TaskManager is a task management application that allows users to create, view, update, and delete tasks. The application provides a convenient RESTful API for interacting with tasks, enabling developers to create client applications that can manage a list of tasks.
## Endpoints

### Get All Tasks
- **HTTP Method:** GET
- **Endpoint:** `/api/v1/tasks`
- **Description:** This endpoint retrieves a list of all tasks.

### Get Task Details by ID
- **HTTP Method:** GET
- **Endpoint:** `/api/v1/tasks/{id}`
- **Description:** This endpoint retrieves the details of a specific task by its unique ID.

### Create Task
- **HTTP Method:** POST
- **Endpoint:** `/api/v1/tasks`
- **Description:** This endpoint allows you to create a new task by providing the task details in the request body.

### Update Task
- **HTTP Method:** PUT
- **Endpoint:** `/api/v1/tasks`
- **Description:** This endpoint allows you to update an existing task by providing the updated task details in the request body.

### Delete Task by ID
- **HTTP Method:** DELETE
- **Endpoint:** `/api/v1/tasks/{id}`
- **Description:** This endpoint allows you to delete a specific task by its unique ID.

### Finish Task by ID
- **HTTP Method:** PUT
- **Endpoint:** `/api/v1/tasks/{id}`
- **Description:** This endpoint allows you to change status to FINISHED of a specific task by its unique ID.

### Get sorted tasks

- **HTTP Method:** GET
- **Endpoint:** `/api/v1/tasks/sorted/{filter}`
- **Description:** This endpoint allows you to search for tasks sorted by given column name.

### TaskDetails class
- **UUID id**
- **String title**
- **String description**
- **LocalDateTime deadline**
- **TaskStatus status**

#### TaskStatus class
- OPEN
- FINISHED
- IN_PROGRESS

## Cross-Origin Resource Sharing (CORS)
The controller is configured to allow cross-origin requests from "http://localhost:63343/". This is useful when you want to make requests to this API from a web application running on a different domain.

## Usage

To use these endpoints, you can make HTTP requests to the specified URLs using tools like `curl`, Postman, or from
frontend application written in TaskManagerFront repository.

