package ru.kpfu.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dto.request.ProjectRequest;
import ru.kpfu.itis.dto.response.ProjectResponse;
import ru.kpfu.itis.exceptions.TripleTAccessDeniedException;
import ru.kpfu.itis.exceptions.TripleTProjectNotFoundException;
import ru.kpfu.itis.exceptions.TripleTProjectRestNotFoundException;
import ru.kpfu.itis.exceptions.TripleTRestAccessDeniedException;
import ru.kpfu.itis.models.Account;
import ru.kpfu.itis.models.Project;
import ru.kpfu.itis.models.Task;
import ru.kpfu.itis.repositories.ProjectRepository;
import ru.kpfu.itis.security.details.AccountUserDetails;
import ru.kpfu.itis.services.ProjectService;
import ru.kpfu.itis.utils.converters.DateConverter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.kpfu.itis.dto.response.ProjectResponse.from;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final DateConverter dateConverter;

    @Override
    public List<ProjectResponse> getProjects(UserDetails userDetails) {
        Long accountId = ((AccountUserDetails)userDetails).getAccount().getId();
        return from(projectRepository.findAllByAccountIdWithDuration(accountId), dateConverter);
    }

    @Override
    public List<ProjectResponse> addProject(ProjectRequest projectRequest, UserDetails userDetails) {
        Account account = ((AccountUserDetails)userDetails).getAccount();

        Project project = Project.builder()
                .name(projectRequest.getName())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .account(account)
                .build();
        projectRepository.save(project);

        return from(projectRepository.findAllByAccountIdWithDuration(account.getId()), dateConverter);
    }

    @Override
    public ProjectResponse getProject(Long projectId, UserDetails userDetails) {
        Project project = this.checkAccessToProject(projectId, userDetails);

        project.setDuration(project.getTasks().stream().mapToInt(Task::getDuration).sum());
        return from(project, dateConverter);
    }

    @Override
    public List<ProjectResponse> deleteProject(Long projectId, UserDetails userDetails) {
        this.checkAccessToProjectRest(projectId, userDetails);
        projectRepository.deleteById(projectId);
        Account account = ((AccountUserDetails)userDetails).getAccount();
        return from(projectRepository.findAllByAccountIdWithDuration(account.getId()), dateConverter);
    }

    @Override
    public Optional<Project> getProjectById(Long projectId) {
        return projectRepository.getProjectById(projectId);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project checkAccessToProjectRest(Long projectId, UserDetails userDetails) {
        Project project = this.getProjectById(projectId).orElseThrow(TripleTProjectRestNotFoundException::new);

        if(!project.getAccount().getId().equals(((AccountUserDetails) userDetails).getAccount().getId())){
            throw new TripleTRestAccessDeniedException("Access to project was denied");
        }
        return project;
    }

    @Override
    public Project checkAccessToProject(Long projectId, UserDetails userDetails) {
        Project project = this.getProjectById(projectId).orElseThrow(TripleTProjectNotFoundException::new);

        if(!project.getAccount().getId().equals(((AccountUserDetails) userDetails).getAccount().getId())){
            throw new TripleTAccessDeniedException("Access to project was denied");
        }
        return project;
    }
}
