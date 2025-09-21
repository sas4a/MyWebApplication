package ru.anik.springtodo.mywebapplication.mapper;

import ru.anik.springtodo.mywebapplication.dto.TaskDto;
import ru.anik.springtodo.mywebapplication.entity.Task;

public class TaskMapper {
    public static TaskDto toDto(Task task) {
        return TaskDto.builder().
                id(task.getId()).
                description(task.getDescription()).
                title(task.getTitle()).
                dueDate(task.getDueDate()).
                status(task.getStatus()).
                build();
    }

    public static Task toEntity(TaskDto dto) {
        return Task.builder().
                id(dto.getId()).
                title(dto.getTitle()).
                description(dto.getDescription()).
                dueDate(dto.getDueDate()).
                status(dto.getStatus()).
                build();
    }
}
