package com.example.CrudApp.Controllers;


import com.example.CrudApp.Models.Task;
import com.example.CrudApp.Services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/index")
    public ModelAndView myPage(Model model) {
        ModelAndView t= new ModelAndView("task"); //nom template pas le model
        model.addAttribute("message", "Hello from Spring Boot!");
        model.addAttribute("task", new Task());
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);

        return t; // My Task Thymeleaf template
    }

    @GetMapping("/addTask")
    public ModelAndView showTaskForm(Model model) {
        model.addAttribute("task", new Task());

        return new ModelAndView("task-form"); // The name of your Thymeleaf template
    }
    @PostMapping(value = "/addTask")
    @Validated
    private ModelAndView createTask(@RequestBody @ModelAttribute("task") Task task) {

        taskService.createTask(task);

        return new ModelAndView("add-task-success");
    }

    @GetMapping("tasks/edit/{id}")
    public ModelAndView showEditTaskForm(@PathVariable Long id , Model m){
    Task t= taskService.getTaskById(id);
    m.addAttribute("task",t);


    return  new ModelAndView("edit-task-form");
    }
    @PostMapping("tasks/update/{id}")
    @Validated
    public ModelAndView updateTask(@PathVariable Long id, @RequestBody @ModelAttribute("task") Task updatedTask) {
        taskService.updateTask(id, updatedTask);

        return new ModelAndView("update-task-success");
    }

    @GetMapping("/tasks")
    private List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    private Task getTask(@PathVariable("id") Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/tasks/delete/{id}")
    public ModelAndView deleteTask(@PathVariable Long id) {
        Task deletedTask = taskService.getTaskById(id);
        if (deletedTask != null) {
            taskService.deleteTask(id);
            ModelAndView mav = new ModelAndView("delete-task-success");
            mav.addObject("task", deletedTask);
            return mav;
        } else {
            // Handle the case where the task doesn't exist, e.g., return an error view.
            ModelAndView errorMav = new ModelAndView("error-page");
            return errorMav;
        }
    }



}
