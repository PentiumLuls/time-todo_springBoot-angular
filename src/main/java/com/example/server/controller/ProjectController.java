package com.example.server.controller;

import com.example.server.dto.TaskDto;
import com.example.server.repo.Project;
import com.example.server.repo.ProjectRepository;
import com.example.server.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "https://time-todo-app.herokuapp.com/")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project getProjectByName(@PathVariable("id") Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET)
    public List<TaskDto> getTasksByProjectName(@PathVariable("id") Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            return taskRepository.findAllByProjectId(project.getId()).stream()
                    .map(TaskDto::toDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
        Project proj = projectRepository.findById(project.getId()).orElse(null);
        if (proj == null) {
            return null;
        }

        proj.setDescriptions(project.getDescriptions());
        proj.setName(project.getName());
        proj.setPriority(project.getPriority());
        projectRepository.save(proj);
        return proj;
    }

    @PostMapping
    public Project addNewProject(@RequestBody Project project) {
        projectRepository.save(project);
        return project;
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id) {
        projectRepository.deleteById(id);
    }
}
