package ru.gb.Homework12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.Homework12.model.Task;
import ru.gb.Homework12.model.TaskStatus;
import ru.gb.Homework12.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    // Добавление задачи
    public Task add(Task task) {
        return repository.save(task);
    }

    // Просмотр всех задач
    public List<Task> getAll() {
        return repository.findAll();
    }

    // Просмотр задач по статусу
    public List<Task> getByStatus(TaskStatus status) {
        return repository.findAll().stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    // Изменение статуса задачи
    public Task update(Long id, Task task) {
        Optional<Task> optionalTask = repository.findById(id);

        if (optionalTask.isPresent()) {
            Task updatedTask = optionalTask.get();

            updatedTask.setStatus(task.getStatus());
            updatedTask.setCreationDate(LocalDateTime.now());

            return updatedTask;
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    // Удаление задачи
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
