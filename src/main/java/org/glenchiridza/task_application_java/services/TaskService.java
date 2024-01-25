package org.glenchiridza.task_application_java.services;

import org.glenchiridza.task_application_java.dto.TaskDto;
import org.glenchiridza.task_application_java.dto.requests.TaskCreationRequest;
import org.glenchiridza.task_application_java.models.Task;
import org.glenchiridza.task_application_java.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private TaskDto convertEntityToDto(Task task){
        return new TaskDto(
                task.getId(),
                task.getDescription(),
                task.getIsReminderOn(),
                task.getIsTaskPending(),
                task.getCreated_date(),
                task.getPriority()
        );
    }
    //assign values to entity

    private void assignValuesToEntity(Task task, TaskCreationRequest createRequest){
        task.setDescription(createRequest.getDescription());
        task.setIsReminderOn(createRequest.getIsReminderOn());
        task.setIsTaskPending(createRequest.getIsTaskPending());
        task.setCreated_date(createRequest.getCreationDate());
        task.setPriority(createRequest.getPriority());

    }

}
