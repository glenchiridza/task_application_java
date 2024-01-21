package org.glenchiridza.task_application_java.repositories;

import org.glenchiridza.task_application_java.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Task findTaskById(Long id);

    @Query(value = "SELECT * FROM task WHERE is_task_pending = TRUE",nativeQuery = true)
    List<Task> getAllPendingTasks();

    @Query(value = "SELECT * FROM task WHERE is_task_pending = FALSE",nativeQuery = true)
    List<Task> getAllClosedTasks();


    @Query(value = "SELECT CASE WHERE COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM task t WHERE t.description = ?1")
    boolean descriptionExists(String description);
}
