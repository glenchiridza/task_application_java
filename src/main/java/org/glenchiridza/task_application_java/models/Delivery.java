package org.glenchiridza.task_application_java.models;


import jakarta.persistence.*;
import lombok.Data;
import org.glenchiridza.task_application_java.enums.Priority;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "delivery", uniqueConstraints = {@UniqueConstraint(name = "uk_product_reference",columnNames = {"product_reference"})})
public class Delivery {

    @Id
    @GeneratedValue(generator = "delivery_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "delivery_sequence", sequenceName = "delivery_sequence",allocationSize = 1)
    private Long id;

    @Column(name = "product_reference",unique = true)
    private String productReference;

    @Column(name = "is_reminder_on",nullable = false)
    private Boolean isReminderOn;

    @Column(name = "is_delivery_pending", nullable = false)
    private Boolean isDeliveryPending = true;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.LOW;



}
