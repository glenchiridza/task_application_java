package org.glenchiridza.task_application_java.services;

import org.glenchiridza.task_application_java.dto.TaskDto;
import org.glenchiridza.task_application_java.dto.requests.TaskCreationRequest;
import org.glenchiridza.task_application_java.dto.requests.TaskUpdateRequest;
import org.glenchiridza.task_application_java.exceptions.BadRequestFoundException;
import org.glenchiridza.task_application_java.exceptions.TaskNotFoundException;
import org.glenchiridza.task_application_java.models.Task;
import org.glenchiridza.task_application_java.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private TaskDto convertEntityToDto(Task task){
        return new TaskDto(
                task.getId(),
                task.getDescription(),
                task.getIsReminderOn(),
                task.getIsTaskPending(),
                task.getCreatedDate(),
                task.getPriority()
        );
    }
    //assign values to entity

    private void assignValuesToEntity(Task task, TaskCreationRequest createRequest){
        task.setDescription(createRequest.getDescription());
        task.setIsReminderOn(createRequest.getIsReminderOn());
        task.setIsTaskPending(createRequest.getIsTaskPending());
        task.setPriority(createRequest.getPriority());

    }

    private void verifyTaskId(Long id){
        if(!taskRepository.existsById(id)){
            throw new TaskNotFoundException("task with the specified id was not found");
        }
    }

    public List<TaskDto> getAllTasks(){
        return taskRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TaskDto> getAllPendingTasks(){
        return taskRepository.getAllPendingTasks().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TaskDto> getAllClosedTasks(){
        return taskRepository.getAllClosedTasks().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public TaskDto getTaskById(Long id){
        verifyTaskId(id);
        Task task = taskRepository.findTaskById(id);
        return convertEntityToDto(task);
    }

    public TaskDto createTask(TaskCreationRequest creationRequest){
        if(taskRepository.descriptionExists(creationRequest.getDescription())){
            throw new BadRequestFoundException("The task with the given description already exists");
        }
        Task task = new Task();
        assignValuesToEntity(task,creationRequest);
        Task savedTask = taskRepository.save(task);
        return convertEntityToDto(savedTask);
    }

    public TaskDto updateTask(Long id, TaskUpdateRequest updateRequest) throws IllegalAccessException {
        verifyTaskId(id);

        Task existingTask = taskRepository.findTaskById(id);
        for(Field prop : TaskUpdateRequest.class.getDeclaredFields()) {
            if (prop.get(updateRequest) != null) {
                Field field = ReflectionUtils.findField(Task.class,prop.getName());
                if(field != null){
                    ReflectionUtils.setField(field,existingTask,prop.get(updateRequest));}
            }
        }
        Task saveTask  = taskRepository.save(existingTask);
        return convertEntityToDto(saveTask);
    }

    public String deleteTask(Long id){
        verifyTaskId(id);
        taskRepository.deleteById(id);
        return "task at "+id+" has been deleted";
    }
}

