package com.example.server.dto;

import com.example.server.repo.Project;
import com.example.server.repo.Tag;
import com.example.server.repo.Task;

import java.util.Set;

public class TaskDto {

    private Long id;
    private String name;
    private Long projectId;
    private int priority;
    private String duration;
    private String startDate;
    private Set<Tag> tags;

    public static Task toTask(TaskDto dto, Project project) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setProject(project);
        task.setPriority(dto.getPriority());
        task.setStartDate(dto.getStartDate());
        task.setDuration(dto.getDuration());
        task.setTags(dto.getTags());
        return task;
    }

    public static TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setProjectId(task.getProject().getId());
        dto.setPriority(task.getPriority());
        dto.setStartDate(task.getStartDate());
        dto.setDuration(task.getDuration());
        dto.setTags(task.getTags());
        return dto;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
