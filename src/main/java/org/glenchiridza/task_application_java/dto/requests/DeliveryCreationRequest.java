package org.glenchiridza.task_application_java.dto.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.glenchiridza.task_application_java.enums.Priority;

@Data
@AllArgsConstructor
public class DeliveryCreationRequest {

    private String productReference;
    private Boolean isReminderOn;
    private Boolean isDeliveryPending;
    private Priority priority;
}
