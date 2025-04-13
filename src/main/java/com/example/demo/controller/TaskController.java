package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*") // Allow access from Angular 
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    // STATIC CRUD ROUTES

    @GetMapping("/static")
    public List<Task> getStaticTasks() {
        return service.getAllStaticTasks();
    }

    @PostMapping("/static")
    public Task addStaticTask(@RequestBody Task task) {
        return service.addStaticTask(task);
    }

    @DeleteMapping("/static/{id}")
    public void deleteStaticTask(@PathVariable Long id) {
        service.deleteStaticTask(id);
    }

    //  DB CRUD ROUTES

    @GetMapping
    public List<Task> getTasks() {
        return service.getAllTasksFromDb();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.addTaskToDb(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return service.updateTaskInDb(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTaskFromDb(id);
    }
}
