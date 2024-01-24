package org.glenchiridza.task_application_java.dto;

import org.glenchiridza.task_application_java.enums.Priority;

import java.time.LocalDateTime;

public class TaskDto {

    private Long id;
    private String description;
    private Boolean isReminderOn;
    private Boolean isTaskPending;

    private LocalDateTime createdDate;
    private Priority priority;
}
