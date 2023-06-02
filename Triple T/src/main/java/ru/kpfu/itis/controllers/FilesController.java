package ru.kpfu.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.services.FileService;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/files")
@RequiredArgsConstructor
@Controller
public class FilesController {

    private final FileService fileService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{file-id}")
    public void getFilesPage(@PathVariable("file-id") Long fileId, HttpServletResponse response,
                             @AuthenticationPrincipal UserDetails userDetails){
        fileService.addFileToResponse(fileId, response, userDetails);
    }
}
