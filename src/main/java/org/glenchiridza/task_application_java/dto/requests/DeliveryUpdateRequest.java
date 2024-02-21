package org.glenchiridza.task_application_java.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.glenchiridza.task_application_java.enums.Priority;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeliveryUpdateRequest {

    private String productReference;
    private Boolean isReminderOn;
    private Boolean isDeliveryPending;
    private LocalDateTime updatedDate;
    private Priority priority;
}
