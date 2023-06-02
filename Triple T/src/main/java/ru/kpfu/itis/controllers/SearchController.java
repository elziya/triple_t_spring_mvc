package ru.kpfu.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.dto.response.SearchTaskResponse;
import ru.kpfu.itis.services.TaskService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/search")
@Controller
public class SearchController {

    private final TaskService taskService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getSearchPage(){
        return "search";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/byTagName", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<SearchTaskResponse>> searchTasksByTagName(@RequestParam("name") String name,
                                                                         @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(taskService.searchTasksByTagName(name, userDetails));
    }
}
