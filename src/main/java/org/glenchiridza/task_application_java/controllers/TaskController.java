package org.glenchiridza.task_application_java.controllers;

import org.glenchiridza.task_application_java.dto.TaskDto;
import org.glenchiridza.task_application_java.dto.requests.TaskCreationRequest;
import org.glenchiridza.task_application_java.dto.requests.TaskUpdateRequest;
import org.glenchiridza.task_application_java.models.Task;
import org.glenchiridza.task_application_java.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("all-tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("pending-tasks")
    public ResponseEntity<List<TaskDto>> getAllPendingTasks(){
        return new ResponseEntity<>(taskService.getAllPendingTasks(),HttpStatus.OK);
    }

    @GetMapping("closed-tasks")
    public ResponseEntity<List<TaskDto>> getAllClosedTasks(){
        return new ResponseEntity<>(taskService.getAllClosedTasks(),HttpStatus.OK);
    }


    @GetMapping("task/{id}")
    public ResponseEntity<TaskDto> getTaskByID(@PathVariable("id") Long id){
        return new ResponseEntity<>(taskService.getTaskById(id),HttpStatus.OK);
    }

    @PostMapping("create-task")
    public ResponseEntity<TaskDto> createTask(
            @RequestBody TaskCreationRequest creationRequest
            ){
        return new ResponseEntity<>(taskService.createTask(creationRequest),HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("id") Long id,
            @RequestBody TaskUpdateRequest updateRequest
            ) throws IllegalAccessException {
        return new ResponseEntity<>(taskService.updateTask(id,updateRequest),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id){
        return new ResponseEntity<>(taskService.deleteTask(id),HttpStatus.OK);
    }
}
