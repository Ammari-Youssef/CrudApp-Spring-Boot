package com.example.CrudApp.Services;


import com.example.CrudApp.DAORepositories.TaskRepository;
import com.example.CrudApp.Models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
    return taskRepository.save(task);

    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        taskRepository.findAll().forEach(t -> tasks.add(t));
        return tasks;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task updateTask(Long id, Task updatedTask) {
        if (taskRepository.existsById(id)) {
            updatedTask.setId(id);
            return taskRepository.save(updatedTask);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
