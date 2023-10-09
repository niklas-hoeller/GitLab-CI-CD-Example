package de.fhac.simpletodo.unit;

import de.fhac.simpletodo.entity.Task;
import de.fhac.simpletodo.exception.RescourceNotFoundException;
import de.fhac.simpletodo.exception.ValidationErrorException;
import de.fhac.simpletodo.repository.TaskRepository;
import de.fhac.simpletodo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task sampleTask;

    @BeforeEach
    public void init() {
        sampleTask = new Task(1L, "Sample Task", false);
    }

    @Test
    public void testFindAllTasks() {
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(sampleTask));
        assertEquals(1, taskService.findAll().size());
        assertEquals(sampleTask, taskService.findAll().get(0));
    }

    @Test
    public void testFindByIdSuccess() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask));
        Task found = taskService.findById(1L);
        assertEquals(sampleTask, found);
    }

    @Test
    public void testFindByIdNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RescourceNotFoundException.class, () -> taskService.findById(1L));
    }

    @Test
    public void testCreateTaskSuccess() {
        when(taskRepository.save(any(Task.class))).thenReturn(sampleTask);
        Task result = taskService.create(sampleTask);
        assertEquals(sampleTask, result);
    }

    @Test
    public void testCreateTaskValidationError() {
        Task invalidTask = new Task(null, "", false);
        assertThrows(ValidationErrorException.class, () -> taskService.create(invalidTask));
    }

    @Test
    public void testUpdateTaskSuccess() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        when(taskRepository.save(any(Task.class))).thenReturn(sampleTask);

        Task updatedTask = new Task(1L, "Updated Task", true);
        Task result = taskService.update(1L, updatedTask);

        assertEquals(updatedTask, result);
    }

    @Test
    public void testUpdateTaskValidationError() {
        assertThrows(ValidationErrorException.class, () ->
                taskService.update(1L, new Task(1L, "", true)));
    }

    @Test
    public void testUpdateTaskNotFound() {
        when(taskRepository.existsById(1L)).thenReturn(false);
        assertThrows(RescourceNotFoundException.class, () -> taskService.update(1L, sampleTask));
    }

    @Test
    public void testDeleteByIdSuccess() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(1L);
        taskService.deleteById(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(taskRepository.existsById(1L)).thenReturn(false);
        assertThrows(RescourceNotFoundException.class, () -> taskService.deleteById(1L));
    }
}
