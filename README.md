# TodoList-CI-CD-Demo

An integrated Todo List application designed as a CI/CD demonstration.

|                  |                                                                        Backend                                                                        |                                                                        Frontend                                                                        |
|------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------:|
| **Coverage**     | ![Backend Coverage](https://gitlab.fz-juelich.de/ptj-spf4/ptj-spf-4-se/continuous-integration-example/badges/main/coverage.svg?job=unit-test-backend) |   ![Frontend Coverage](https://gitlab.fz-juelich.de/ptj-spf4/ptj-spf-4-se/continuous-integration-example/badges/main/coverage.svg?job=test-frontend)   |
| **Build & Test** |  ![Backend Status](https://gitlab.fz-juelich.de/ptj-spf4/ptj-spf-4-se/continuous-integration-example/badges/main/pipeline.svg?job=unit-test-backend)  |    ![Frontend Status](https://gitlab.fz-juelich.de/ptj-spf4/ptj-spf-4-se/continuous-integration-example/badges/main/pipeline.svg?job=test-frontend)    |
| **Deployment**   | ![Backend Deployment](https://gitlab.fz-juelich.de/ptj-spf4/ptj-spf-4-se/continuous-integration-example/badges/main/pipeline.svg?job=deploy-backend)  | ![Frontend Deployment](https://gitlab.fz-juelich.de/ptj-spf4/ptj-spf-4-se/continuous-integration-example/badges/main/pipeline.svg?job=deploy-frontend) |

## Project Overview

This project consists of a Spring Boot backend consumed by an Angular frontend. The components communicate using a
RESTful API. All data is persistently stored on the backend application server using an in-file H2 database.

## Backend

### Task Entity

The `Task` entity represents an entry in the Todo List.

| Field       | Type    | Description                                     |
|-------------|---------|-------------------------------------------------|
| `id`        | Long    | Unique identifier for the task. Auto-generated. |
| `title`     | String  | The title of the task.                          |
| `completed` | boolean | Whether the task is completed or not.           |

### API Endpoints

All API endpoints are prefixed with `/api/v1/tasks`.

| Endpoint | Method   | Description                    | Request Body           | Response                 |
|----------|----------|--------------------------------|------------------------|--------------------------|
| `/`      | `GET`    | Fetches a list of all tasks.   | -                      | Array of `Task` objects. |
| `/{id}`  | `GET`    | Fetches a specific task by ID. | -                      | `Task` object.           |
| `/`      | `POST`   | Creates a new task.            | `Task` object.         | Created `Task` object.   |
| `/{id}`  | `PUT`    | Updates a specific task by ID. | Updated `Task` object. | Updated `Task` object.   |
| `/{id}`  | `DELETE` | Deletes a specific task by ID. | -                      | -                        |

## Frontend

### Routes

| Path | Component       | Description                                                                           |
|------|-----------------|---------------------------------------------------------------------------------------|
| `/`  | `TodoComponent` | Displays a list of all tasks and allows the user to create, update, and delete tasks. |

## Testing

This application includes rudimentary tests for both the frontend and backend applications to demonstrate the testing
capabilities of the CI/CD pipeline.

### Backend

All backend tests use the `test` profile, which uses an in-memory H2 database.

#### Unit Tests

Unit tests are found in the directory `test.java.de.fhac.simpletodo.unit` and follow the naming convention `*Test.java`.
To run the tests, execute the following command from the `backend` directory of the project:

```shell
./mvnw test
```

#### Integration Tests

Integration tests are found in the directory `test.java.de.fhac.simpletodo.integration` and follow the naming convention
`*IT.java`. To run the tests, execute the following command from the `backend` directory of the project:

```shell
./mvnw verify
```

### Frontend

#### Component Tests

Component tests are found in the component directories and follow the naming convention `*.spec.ts`. To run the
tests, execute the following command from the `frontend` directory of the project:

```shell
ng test
```

**Note**: Ensure the Angular CLI is installed.

## Deployment

This project is deployed using GitLab CI/CD. The workflow can be found in `.gitlab-ci.yml`. This project
makes use of the [WinSW](https://github.com/winsw/winsw) Windows Service Wrapper to run both the backend and frontend
applications as Windows services. The services are configured using the XML files found in the root directory of the
project.

**Note**: Adjust the paths in the CI/CD workflow to match your environment.
