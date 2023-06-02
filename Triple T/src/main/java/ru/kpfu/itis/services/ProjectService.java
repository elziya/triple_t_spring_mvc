package ru.kpfu.itis.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.dto.request.ProjectRequest;
import ru.kpfu.itis.dto.response.ProjectResponse;
import ru.kpfu.itis.models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<ProjectResponse> getProjects(UserDetails userDetails);

    List<ProjectResponse> addProject(ProjectRequest projectRequest, UserDetails userDetails);

    ProjectResponse getProject(Long projectId, UserDetails userDetails);

    List<ProjectResponse> deleteProject(Long projectId, UserDetails userDetails);

    Optional<Project> getProjectById(Long projectId);

    Project updateProject(Project project);

    Project checkAccessToProjectRest(Long projectId, UserDetails userDetails);

    Project checkAccessToProject(Long projectId, UserDetails userDetails);
}
