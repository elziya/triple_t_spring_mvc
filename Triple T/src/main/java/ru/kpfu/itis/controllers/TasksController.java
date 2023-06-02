package ru.kpfu.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.dto.request.TaskRequest;
import ru.kpfu.itis.dto.response.ProjectResponse;
import ru.kpfu.itis.services.TaskService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Controller
@RequestMapping("/projects/{project-id}/tasks")
public class TasksController {

    private final TaskService taskService;

    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectResponse> addTask(@RequestBody TaskRequest task,
                                                   @PathVariable("project-id") Long projectId,
                                                   @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(taskService.addTask(task, projectId, userDetails));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/{task-id}")
    public String getTask(@PathVariable("project-id") Long projectId,
                          @PathVariable("task-id") Long taskId,
                          @AuthenticationPrincipal UserDetails userDetails,
                          ModelMap modelMap){
        modelMap.addAttribute("task", taskService.getTask(projectId, taskId, userDetails));
        return "task";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/{task-id}/upload")
    public String getUploadingFilePage(@PathVariable("task-id") Long taskId){
        return "upload_file";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/{task-id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileToTask(@RequestParam("files") MultipartFile[] multipartFiles,
                                                   @PathVariable("task-id") Long taskId,
                                                   @PathVariable("project-id") Long projectId,
                                                   @AuthenticationPrincipal UserDetails userDetails){
        taskService.uploadFile(multipartFiles, taskId, projectId, userDetails);
        return  "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProjects").build()
                + projectId + "/tasks/" + taskId;
    }
}
