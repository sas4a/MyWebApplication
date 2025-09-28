package ru.anik.springtodo.mywebapplication.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.anik.springtodo.mywebapplication.dto.TaskDto;
import ru.anik.springtodo.mywebapplication.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "status", source = "status")
    TaskDto toDto(Task task);

    @InheritInverseConfiguration
    Task toEntity(TaskDto taskDto);
}
