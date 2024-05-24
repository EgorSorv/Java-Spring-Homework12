package ru.gb.Homework12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.Homework12.model.Task;
import ru.gb.Homework12.model.TaskStatus;
import ru.gb.Homework12.service.FileGateway;
import ru.gb.Homework12.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    private final FileGateway gateway;

    // Добавление задачи
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        gateway.writeToFile(task.getTitle() + ".txt", task.toString());
        return service.add(task);
    }

    // Просмотр всех задач
    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAll();
    }

    // Просмотр задач по статусу
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return service.getByStatus(status);
    }

    // Изменение статуса задачи
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        gateway.writeToFile(task.getTitle() + ".txt", task.toString());
        return service.update(id, task);
    }

    // Удаление задачи
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.delete(id);
    }
}
