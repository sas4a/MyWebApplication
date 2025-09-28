package ru.anik.springtodo.mywebapplication.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anik.springtodo.mywebapplication.dto.TaskDto;
import ru.anik.springtodo.mywebapplication.dto.TaskPatchDto;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;
import ru.anik.springtodo.mywebapplication.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskDto createTask(@Valid @RequestBody TaskDto taskDto){
        return taskService.createTask(taskDto);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto){
        return taskService.updateTask(id,taskDto);
    }

    @PatchMapping("/{id}")
    public TaskDto patchTask(@PathVariable Long id, @RequestBody TaskPatchDto patchDto){
        return taskService.patchTask(id, patchDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @GetMapping(params = "status")
    public List<TaskDto> getTaskByStatus(@RequestParam TaskStatus status){
        return taskService.getTasksByStatus(status);
    }

    @GetMapping(params = "sort")
    public List<TaskDto> getAllTasksSorted(@RequestParam String sort) {
        return taskService.getAllTasksSorted(sort);
    }


}
