package ru.kpfu.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.dto.request.TaskRequest;
import ru.kpfu.itis.dto.response.ProjectResponse;
import ru.kpfu.itis.dto.response.SearchTaskResponse;
import ru.kpfu.itis.dto.response.TaskResponse;
import ru.kpfu.itis.exceptions.TripleTTagRestNotFoundException;
import ru.kpfu.itis.exceptions.TripleTTaskNotFoundException;
import ru.kpfu.itis.models.Project;
import ru.kpfu.itis.models.Task;
import ru.kpfu.itis.repositories.TagRepository;
import ru.kpfu.itis.repositories.TaskRepository;
import ru.kpfu.itis.security.details.AccountUserDetails;
import ru.kpfu.itis.services.FileService;
import ru.kpfu.itis.services.ProjectService;
import ru.kpfu.itis.services.TaskService;
import ru.kpfu.itis.utils.converters.DateConverter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;

    private final FileService fileService;
    private final ProjectService projectService;

    private final DateConverter dateConverter;

    public ProjectResponse addTask(TaskRequest taskRequest, Long projectId, UserDetails userDetails) {
        Project project = projectService.checkAccessToProjectRest(projectId, userDetails);
        project.setEndDate(LocalDate.now());

        Task task = Task.builder()
                .name(taskRequest.getName())
                .duration(taskRequest.getDuration())
                .project(projectService.updateProject(project))
                .build();

        if (taskRequest.getTags() != null){

            task.setTags(taskRequest.getTags().stream()
                    .map(tag -> tagRepository.findByTagNameAndAccount_Email(tag, userDetails.getUsername()))
                    .map(optional -> optional.orElseThrow(TripleTTagRestNotFoundException::new))
                    .collect(Collectors.toSet()));
        }

        taskRepository.save(task);

        return projectService.getProject(projectId, userDetails);
    }

    @Override
    public List<SearchTaskResponse> searchTasksByTagName(String name, UserDetails userDetails) {
        Long accountId = ((AccountUserDetails)userDetails).getAccount().getId();

        return SearchTaskResponse.from(taskRepository.findAllByTagLike("%"+name+"%", accountId), dateConverter);
    }

    @Override
    public void uploadFile(MultipartFile[] multipartFiles, Long taskId, Long projectId, UserDetails userDetails) {

        projectService.checkAccessToProject(projectId, userDetails);

        for (MultipartFile multipartFile : multipartFiles) {
            fileService.uploadFile(multipartFile, taskRepository.findByIdAndProject_Id(taskId, projectId)
                    .orElseThrow(TripleTTaskNotFoundException::new));
        }
    }
    @Override
    public TaskResponse getTask(Long projectId, Long taskId, UserDetails userDetails) {
        projectService.checkAccessToProject(projectId, userDetails);
        return TaskResponse.from(taskRepository.findByIdAndProject_Id(taskId, projectId)
                .orElseThrow(TripleTTaskNotFoundException::new));
    }
}
