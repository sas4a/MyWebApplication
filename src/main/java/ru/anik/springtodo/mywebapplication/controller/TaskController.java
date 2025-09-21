package ru.anik.springtodo.mywebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anik.springtodo.mywebapplication.dto.TaskDto;
import ru.anik.springtodo.mywebapplication.entity.Task;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;
import ru.anik.springtodo.mywebapplication.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
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
    public TaskDto createTask(@RequestBody TaskDto taskDto){
        return taskService.createTask(taskDto);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto){
        return taskService.updateTask(id,taskDto);
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
