package ru.kpfu.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.request.ProjectRequest;
import ru.kpfu.itis.dto.response.ProjectResponse;
import ru.kpfu.itis.services.ProjectService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class ProjectsRestController {

    private final ProjectService projectService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectResponse>> addProject(@AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody ProjectRequest project){
        return ResponseEntity.ok(projectService.addProject(project, userDetails));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{project-id}")
    public ResponseEntity<List<ProjectResponse>> deleteProject(@AuthenticationPrincipal UserDetails userDetails,
                                                               @PathVariable("project-id") Long projectId) {
        return ResponseEntity.ok(projectService.deleteProject(projectId, userDetails));
    }
}
