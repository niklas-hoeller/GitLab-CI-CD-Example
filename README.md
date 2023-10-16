# TodoList-CI-CD-Demo

An integrated Todo List application designed as a CI/CD demonstration.

## Project Overview

This project consists of a Spring Boot backend consumed by an Angular frontend. The components communicate using a RESTful API. All data is persistently stored on the backend application server using an in-file H2 database.

## Task Entity

The `Task` entity represents an entry in the Todo List.

| Field        | Type          | Description                                        |
|--------------|---------------|----------------------------------------------------|
| `id`         | Long          | Unique identifier for the task. Auto-generated.    |
| `title`      | String        | The title of the task.                             |
| `completed`  | boolean       | Whether the task is completed or not.              |
| `createdAt`  | LocalDateTime | Timestamp when the task was created.               |
| `updatedAt`  | LocalDateTime | Timestamp when the task was last updated.          |

## API Endpoints

All API endpoints are prefixed with `/api/v1/tasks`.

| Endpoint          | Method   | Description                             | Request Body             | Response                       |
|-------------------|----------|-----------------------------------------|--------------------------|--------------------------------|
| `/`               | `GET`    | Fetches a list of all tasks.            | -                        | Array of `Task` objects.       |
| `/{id}`           | `GET`    | Fetches a specific task by ID.          | -                        | `Task` object.                 |
| `/`               | `POST`   | Creates a new task.                     | `Task` object.           | Created `Task` object.         |
| `/{id}`           | `PUT`    | Updates a specific task by ID.          | Updated `Task` object.   | Updated `Task` object.         |
| `/{id}`           | `DELETE` | Deletes a specific task by ID.          | -                        | -                              |

