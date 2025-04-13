package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private List<Task> staticTasks = new ArrayList<>();

    @Autowired
    private TaskRepository repo;

    //  STATIC METHODS

    public List<Task> getAllStaticTasks() {
        return staticTasks;
    }

    public Task addStaticTask(Task task) {
        task.setId((long) (staticTasks.size() + 1));
        staticTasks.add(task);
        return task;
    }

    public void deleteStaticTask(Long id) {
        staticTasks.removeIf(t -> t.getId().equals(id));
    }

    //  DATABASE METHODS (Hibernate)

    public List<Task> getAllTasksFromDb() {
        return repo.findAll();
    }

    public Task addTaskToDb(Task task) {
        return repo.save(task);
    }

    public Task updateTaskInDb(Long id, Task updated) {
        Task task = repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setPriority(updated.getPriority());
        task.setStatus(updated.getStatus());
        return repo.save(task);
    }

    public void deleteTaskFromDb(Long id) {
        repo.deleteById(id);
    }
}
