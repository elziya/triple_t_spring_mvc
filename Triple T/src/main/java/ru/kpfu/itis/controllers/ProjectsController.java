package ru.kpfu.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.services.ProjectService;
import ru.kpfu.itis.services.TagService;

@RequiredArgsConstructor
@RequestMapping("/projects")
@Controller
public class ProjectsController {

    private final ProjectService projectService;
    private final TagService tagService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getProjects(@AuthenticationPrincipal UserDetails userDetails, ModelMap modelMap){
        modelMap.addAttribute("projects", projectService.getProjects(userDetails));
        return "projects";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{project-id}")
    public String getProject(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("project-id") Long projectId, ModelMap modelMap){
        modelMap.addAttribute("project", projectService.getProject(projectId, userDetails));
        modelMap.addAttribute("tags", tagService.getTagsOfAccount(userDetails));
        return "project";
    }
}
