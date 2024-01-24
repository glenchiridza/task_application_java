package org.glenchiridza.task_application_java.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.glenchiridza.task_application_java.enums.Priority;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task",uniqueConstraints = {@UniqueConstraint(name="uk_task_description",columnNames= {"description"})})
public class Task {

    @Id
    @GeneratedValue(generator = "task_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_sequence",sequenceName = "task_sequence",allocationSize = 1)
    private Long id;

    @Column(name = "description", unique = true)
    private String description;

    @Column(name="is_reminder_on",nullable = false)
    private Boolean isReminderOn;

    @Column(name = "is_task_pending",nullable = false)
    private Boolean isTaskPending =true;

    @Column(name = "created_date",nullable = false)
    private LocalDateTime created_date;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


    private org.glenchiridza.task_application_java.enums.Priority Priority;

}
