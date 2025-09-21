package ru.anik.springtodo.mywebapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.anik.springtodo.mywebapplication.dto.TaskDto;
import ru.anik.springtodo.mywebapplication.entity.Task;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;
import ru.anik.springtodo.mywebapplication.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImpTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskServiceImpl service;

    @Test
    void getAllTasks_shoudReturnAllTasks(){
        Task task1 = new Task(6L,"Task6", "Wash dishes",
                LocalDate.of(2025,9,17), TaskStatus.TODO);
        Task task2 = new Task(7L,"Task7", "Walk with Dog",
                LocalDate.of(2024,3,15), TaskStatus.TODO);
        Task task3 = new Task(9L,"Task9", "Play PC",
                LocalDate.of(2025,9,10), TaskStatus.IN_PROGRESS);

        Mockito.when(taskRepository.findAll()).thenReturn(List.of(task1,task2,task3));
        List<TaskDto> listDto = service.getAllTasks();

        assertEquals(3, listDto.size());
        assertEquals("Task7", listDto.get(1).getTitle());
        assertEquals(9L, listDto.get(2).getId());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void getTaskById_shoudReturnTrue_noTask(){
        Mockito.when(taskRepository.findById(100L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.getTaskById(100L));
        assertTrue(ex.getMessage().contains("not found"));

        verify(taskRepository, times(1)).findById(99L);
    }


   /* //Тут еще домучаю тесты и этот доделаю или переделаю
*//*    @Test
    void deleteTask_shoudReturnSizeZero(){
        Task task1 = new Task(6L,"Task6", "Wash dishes",
                LocalDate.of(2025,9,17), TaskStatus.TODO);
        when(taskRepository.deleteById(6L)).thenReturn()*//*
    }*/
//public void deleteTask(Long id) {
//        if(!taskrepository.existsById(id)){
//            throw new RuntimeException("Task not found with id: " + id);
//        }
//        taskrepository.deleteById(id);
//    }




}
