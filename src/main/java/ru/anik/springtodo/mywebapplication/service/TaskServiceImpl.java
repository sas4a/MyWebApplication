package ru.anik.springtodo.mywebapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anik.springtodo.mywebapplication.dto.TaskDto;
import ru.anik.springtodo.mywebapplication.dto.TaskPatchDto;
import ru.anik.springtodo.mywebapplication.entity.Task;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;
import ru.anik.springtodo.mywebapplication.exception.TaskNotFoundException;
import ru.anik.springtodo.mywebapplication.mapper.TaskMapper;
import ru.anik.springtodo.mywebapplication.repository.TaskRepository;

import static org.springframework.data.domain.Sort.by;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task saveTask = taskRepository.save(task);
        return taskMapper.toDto(saveTask);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setDueDate(taskDto.getDueDate());
        existingTask.setStatus(taskDto.getStatus());

        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.toDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public List<TaskDto> getAllTasksSorted(String sortBy) {
        return taskRepository.findAll(by(sortBy))
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public TaskDto patchTask(Long id, TaskPatchDto patchDto) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
        if(patchDto.getTitle() != null){
            task.setTitle(patchDto.getTitle());
        }
        if(patchDto.getDescription() != null){
            task.setDescription(patchDto.getDescription());
        }
        if (patchDto.getDueDate() != null) {
            task.setDueDate(patchDto.getDueDate());
        }
        if (patchDto.getStatus() != null) {
            task.setStatus(patchDto.getStatus());
        }

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

}
