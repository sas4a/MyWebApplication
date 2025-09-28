package ru.anik.springtodo.mywebapplication.service;
import ru.anik.springtodo.mywebapplication.dto.TaskDto;
import ru.anik.springtodo.mywebapplication.dto.TaskPatchDto;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id);
    TaskDto createTask(TaskDto taskdto);
    TaskDto updateTask(Long id, TaskDto taskdto);
    void deleteTask(Long id);
    List<TaskDto> getTasksByStatus(TaskStatus status);
    List<TaskDto> getAllTasksSorted(String sortBy);
    TaskDto patchTask(Long id, TaskPatchDto patchDto);
}
