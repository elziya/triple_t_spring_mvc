package ru.kpfu.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.request.TagRequest;
import ru.kpfu.itis.dto.response.TagResponse;
import ru.kpfu.itis.services.TagService;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RequestMapping("/tags")
@Controller
public class TagsController {

    private final TagService tagService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getTags(@AuthenticationPrincipal UserDetails userDetails,  ModelMap modelMap){
        modelMap.addAttribute("tags", tagService.getTagsOfAccount(userDetails));
        return "tags";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Set<TagResponse>> addTask(@AuthenticationPrincipal UserDetails userDetails,
                                                    @RequestBody TagRequest tag){
        return ResponseEntity.ok(tagService.addTag(tag, userDetails));
    }
}
