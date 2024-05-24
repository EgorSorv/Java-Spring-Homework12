package ru.gb.Homework12.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название задачи
    @Column(nullable = false)
    private String title;

    // Описание задачи
    @Column(nullable = false)
    private String description;

    // Статус задачи
    @Column(nullable = false)
    private TaskStatus status;

    // Время создания задачи
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    // Присвоение задаче текущей даты при создании
    @PrePersist
    public void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }
}

