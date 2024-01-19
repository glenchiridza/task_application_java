package org.glenchiridza.task_application_java.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "task",uniqueConstraints = {UniqueConstraint(name="uk_task_description",columnNames= "")})
public class Task {

    @Id
    @GeneratedValue(generator = "task_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_sequence",sequenceName = "task_sequence",allocationSize = 1)
    private int id;

}
