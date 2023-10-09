package de.fhac.simpletodo.service;

import de.fhac.simpletodo.entity.Task;
import de.fhac.simpletodo.exception.RescourceNotFoundException;
import de.fhac.simpletodo.exception.ValidationErrorException;
import de.fhac.simpletodo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RescourceNotFoundException("Task with id " + id + " does not exist"));
    }

    public Task create(Task task) {
        if (task.getTitle().isEmpty()) {
            throw new ValidationErrorException("Title must not be empty");
        }

        task.setId(null);
        return taskRepository.save(task);
    }

    public Task update(Long id, Task updatedTask) throws IllegalArgumentException {
        if (updatedTask.getTitle().isEmpty()) {
            throw new ValidationErrorException("Title must not be empty");
        }

        if (!taskRepository.existsById(id)) {
            throw new RescourceNotFoundException("Task with id " + id + " does not exist");
        }

        updatedTask.setId(id);
        return taskRepository.save(updatedTask);
    }

    public void deleteById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RescourceNotFoundException("Task with id " + id + " does not exist");
        }

        taskRepository.deleteById(id);
    }
}
