package org.glenchiridza.task_application_java.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.glenchiridza.task_application_java.enums.Priority;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskCreationRequest {


    private  String description;
    private Boolean isReminderOn;
    private Boolean isTaskPending;
    private LocalDateTime creationDate;
    private Priority priority;
}
