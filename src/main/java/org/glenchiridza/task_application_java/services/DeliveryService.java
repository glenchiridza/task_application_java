package org.glenchiridza.task_application_java.services;

import org.glenchiridza.task_application_java.dto.DeliveryDto;
import org.glenchiridza.task_application_java.dto.TaskDto;
import org.glenchiridza.task_application_java.dto.requests.DeliveryCreationRequest;
import org.glenchiridza.task_application_java.dto.requests.DeliveryUpdateRequest;
import org.glenchiridza.task_application_java.exceptions.BadRequestFoundException;
import org.glenchiridza.task_application_java.exceptions.TaskNotFoundException;
import org.glenchiridza.task_application_java.models.Delivery;
import org.glenchiridza.task_application_java.repositories.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

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
    }private void assignValuesToEntity(Delivery delivery, DeliveryCreationRequest requestDto){
        delivery.setIsDeliveryPending(requestDto.getIsDeliveryPending());
        delivery.setIsReminderOn(requestDto.getIsReminderOn());
        delivery.setPriority(requestDto.getPriority());
        delivery.setProductReference(requestDto.getProductReference());
    }


    private void verifyDeliveryId(Long id){
        if(!deliveryRepository.existsById(id)){
            throw new TaskNotFoundException("Delivery with given id not found");
        }
    }


    public List<DeliveryDto> getAllDeliveries(){
        return deliveryRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<DeliveryDto> getAllPendingDeliveries(){
        return deliveryRepository.getAllPendingDeliveries().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<DeliveryDto> getAllCompletedDeliveries(){
        return deliveryRepository.getAllCompletedDeliveries().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public DeliveryDto getDeliveryById(Long id){
        verifyDeliveryId(id);
        Delivery delivery = deliveryRepository.findDeliveriesById(id);
        return convertEntityToDto(delivery);
    }

    public DeliveryDto createDelivery(DeliveryCreationRequest requestDto){
        if(deliveryRepository.productReferenceExists(requestDto.getProductReference())){
            throw new BadRequestFoundException("Delivery with the given reference does not exist");
        }
        Delivery delivery = new Delivery();
        assignValuesToEntity(delivery,requestDto);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return convertEntityToDto(savedDelivery);
    }

    public DeliveryDto updateDelivery(Long id, DeliveryUpdateRequest updateRequest) throws IllegalAccessException {
        verifyDeliveryId(id);

        Delivery existingDelivery = deliveryRepository.findDeliveriesById(id);
        for(Field prop : DeliveryUpdateRequest.class.getDeclaredFields()) {
            if (prop.get(updateRequest) != null) {
                Field field = ReflectionUtils.findField(Delivery.class, prop.getName());
                if (field != null) {
                    ReflectionUtils.setField(field, existingDelivery, prop.get(updateRequest));
                }
            }
        }
            Delivery updatedDelivery = deliveryRepository.save(existingDelivery);
            return convertEntityToDto(updatedDelivery);

    }


    public String deleteDelivery(Long id) {
        verifyDeliveryId(id);
        deliveryRepository.deleteById(id);
        return "delivery at "+ id + " has been deleted";
    }
}
