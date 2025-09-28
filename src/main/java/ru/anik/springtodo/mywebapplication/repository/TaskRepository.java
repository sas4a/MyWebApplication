package ru.anik.springtodo.mywebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anik.springtodo.mywebapplication.entity.Task;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByStatus(TaskStatus taskStatus);

}
