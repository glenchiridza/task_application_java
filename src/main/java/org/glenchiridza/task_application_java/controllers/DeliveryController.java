package org.glenchiridza.task_application_java.controllers;


import org.glenchiridza.task_application_java.dto.DeliveryDto;
import org.glenchiridza.task_application_java.dto.requests.DeliveryCreationRequest;
import org.glenchiridza.task_application_java.dto.requests.DeliveryUpdateRequest;
import org.glenchiridza.task_application_java.services.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/all-deliveries")
    public ResponseEntity<List<DeliveryDto>> getAllDeliveries(){
        return new ResponseEntity<>(deliveryService.getAllDeliveries(), HttpStatus.OK);
    }

    @GetMapping("/pending-tasks")
    public ResponseEntity<List<DeliveryDto>> getAllPendingTasks(){
        return new ResponseEntity<>(deliveryService.getAllPendingDeliveries(),HttpStatus.OK);
    }

    @GetMapping("/completed-deliveries")
    public ResponseEntity<List<DeliveryDto>> getAllCompletedDeliveries(){
        return new ResponseEntity<>(deliveryService.getAllCompletedDeliveries(),HttpStatus.OK);
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable("id") Long id){
        return new ResponseEntity<>(deliveryService.getDeliveryById(id),HttpStatus.OK);
    }

    @PostMapping("/create-delivery")
    public ResponseEntity<DeliveryDto> createDelivery(@RequestBody DeliveryCreationRequest requestDto){
        return new ResponseEntity<>(deliveryService.createDelivery(requestDto),HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<DeliveryDto> updateDelivery(
            @PathVariable("id") Long id,
            @RequestBody DeliveryUpdateRequest requestDto
            ) throws IllegalAccessException {
        return new ResponseEntity<>(deliveryService.updateDelivery(id,requestDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDelivery(@PathVariable("id") Long id){
        return new ResponseEntity<>(deliveryService.deleteDelivery(id),HttpStatus.OK);
    }

}
