package ru.anik.springtodo.mywebapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anik.springtodo.mywebapplication.dto.TaskDto;
import ru.anik.springtodo.mywebapplication.entity.Task;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;
import ru.anik.springtodo.mywebapplication.mapper.TaskMapper;
import ru.anik.springtodo.mywebapplication.repository.TaskRepository;
import static org.springframework.data.domain.Sort.by;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskrepository;

    @Override
    public List<TaskDto> getAllTasks() {

        return taskrepository.findAll()
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskrepository.findById(id)
                .orElseThrow(()->new RuntimeException("Task not found with id: " + id));
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = TaskMapper.toEntity(taskDto);
        Task saveTask = taskrepository.save(task);
        return TaskMapper.toDto(saveTask);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskrepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found with id " + id));
        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setDueDate(taskDto.getDueDate());
        existingTask.setStatus(taskDto.getStatus());

        Task updatedTask = taskrepository.save(existingTask);
        return TaskMapper.toDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        if(!taskrepository.existsById(id)){
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskrepository.deleteById(id);
    }

    @Override
    public List<TaskDto> getTasksByStatus(TaskStatus status) {
        return  taskrepository.findByStatus(status)
                .stream()
                .map(TaskMapper::toDto)
                .toList();
    }

    @Override
    public List<TaskDto> getAllTasksSorted(String sortBy) {
        return taskrepository.findAll(by(sortBy))
                .stream()
                .map(TaskMapper::toDto)
                .toList();
    }
}
