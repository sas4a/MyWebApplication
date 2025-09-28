package ru.anik.springtodo.mywebapplication.dto;

import lombok.Data;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;

import java.time.LocalDate;

@Data
public class TaskPatchDto {
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;
}
