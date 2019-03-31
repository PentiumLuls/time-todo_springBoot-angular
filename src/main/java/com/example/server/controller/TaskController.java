package com.example.server.controller;

import com.example.server.dto.TaskDto;
import com.example.server.repo.Project;
import com.example.server.repo.ProjectRepository;
import com.example.server.repo.Task;
import com.example.server.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "https://time-todo-app.herokuapp.com/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskRepository.findAll().stream()
                .map(TaskDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/generate-new-id")
    public Long generateNewTaskId() {
        Long id = 0L;
        for (Task task : taskRepository.findAll()) {
            if (task.getId() > id) {
                id = task.getId();
            }
        }
        return id + 1;
    }

    @PostMapping
    public TaskDto addNewTask(@RequestBody TaskDto dto) {
        Project project = projectRepository.findById(dto.getProjectId()).orElse(null);
        Task task = TaskDto.toTask(dto, project);
        taskRepository.save(task);
        return dto;
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable("id") Long id, @RequestBody TaskDto dto) {
        Project project = projectRepository.findById(dto.getProjectId()).orElse(null);
        Task task = TaskDto.toTask(dto, project);
        this.taskRepository.save(task);
        return dto;
    }

    @DeleteMapping("/{id}")
    public TaskDto deleteTask(@PathVariable("id") Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        taskRepository.delete(task);
        if (task != null)
            return TaskDto.toDto(task);
        return null;
    }
}
