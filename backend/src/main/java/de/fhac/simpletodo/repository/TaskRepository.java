package de.fhac.simpletodo.repository;

import de.fhac.simpletodo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
