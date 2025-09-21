package ru.anik.springtodo.mywebapplication.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.anik.springtodo.mywebapplication.entity.TaskStatus;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;
}
