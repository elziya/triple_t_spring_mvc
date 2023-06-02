package ru.kpfu.itis.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.dto.request.TaskRequest;
import ru.kpfu.itis.dto.response.ProjectResponse;
import ru.kpfu.itis.dto.response.SearchTaskResponse;
import ru.kpfu.itis.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {

    ProjectResponse addTask(TaskRequest taskRequest, Long projectId, UserDetails userDetails);

    List<SearchTaskResponse> searchTasksByTagName(String name, UserDetails userDetail);

    void uploadFile(MultipartFile[] multipartFiles, Long taskId, Long projectId, UserDetails userDetails);

    TaskResponse getTask(Long projectId, Long taskId, UserDetails userDetails);
}
