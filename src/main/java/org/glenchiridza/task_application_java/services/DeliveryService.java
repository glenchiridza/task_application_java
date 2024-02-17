package org.glenchiridza.task_application_java.services;

import org.glenchiridza.task_application_java.dto.DeliveryDto;
import org.glenchiridza.task_application_java.models.Delivery;
import org.glenchiridza.task_application_java.repositories.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;


    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    private DeliveryDto convertEntityToDto(Delivery delivery){
        return new DeliveryDto(
                delivery.getId(),
                delivery.getProductReference(),
                delivery.getIsReminderOn(),
                delivery.getIsDeliveryPending(),
                delivery.getCreatedDate(),
                delivery.getUpdatedDate(),
                delivery.getPriority()

        );
    }private void assignValuesToEntity(Delivery delivery, CreateDe)




}
