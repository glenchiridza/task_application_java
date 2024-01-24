package org.glenchiridza.task_application_java.dto.requests;

import org.glenchiridza.task_application_java.enums.Priority;

import java.time.LocalDateTime;

public class TaskCreationRequest {


    private  String description;
    private Boolean isReminderOn;
    private Boolean isTaskPending;
    private LocalDateTime creationDate;
    private Priority priority;
}
