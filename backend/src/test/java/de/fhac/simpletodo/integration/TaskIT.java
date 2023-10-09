package de.fhac.simpletodo.integration;

import de.fhac.simpletodo.entity.Task;
import de.fhac.simpletodo.exception.ErrorDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndUpdateAndRetrieveAndDeleteTask() {
        Task newTask = new Task();
        newTask.setTitle("Test Task");

        // CREATE
        ResponseEntity<Task> createResponse = restTemplate
                .postForEntity("http://localhost:" + port + "/api/tasks", newTask, Task.class);
        assertThat(createResponse.getStatusCode().is2xxSuccessful()).isTrue();
        Task createdTask = createResponse.getBody();
        assertThat(createdTask).isNotNull();
        assertThat(createdTask.getId()).isNotNull();
        assertThat(createdTask.getTitle()).isEqualTo("Test Task");

        // UPDATE
        createdTask.setTitle("Updated Test Task");
        restTemplate.put("http://localhost:" + port + "/api/tasks/" + createdTask.getId(), createdTask);

        // RETRIEVE UPDATED
        Task updatedTask = restTemplate
                .getForObject("http://localhost:" + port + "/api/tasks/" + createdTask.getId(), Task.class);
        assertThat(updatedTask.getTitle()).isEqualTo("Updated Test Task");

        // DELETE
        restTemplate.delete("http://localhost:" + port + "/api/tasks/" + updatedTask.getId());

        // RETRIEVE AGAIN
        ResponseEntity<ErrorDetails> errorResponse = restTemplate
                .getForEntity("http://localhost:" + port + "/api/tasks/" + updatedTask.getId(), ErrorDetails.class);
        assertThat(errorResponse.getStatusCode().value()).isEqualTo(404);
    }
}

