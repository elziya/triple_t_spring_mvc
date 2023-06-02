package ru.kpfu.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.utils.VkOAuthUtility;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/signin")
@Controller
public class SignInController {

    private final VkOAuthUtility vkOAuthUtility;

    @PreAuthorize("isAnonymous()")
    @GetMapping
    public String getSignInPage(@RequestParam(required = false) String error, ModelMap map, HttpServletRequest request){
        map.put("vkRequest", vkOAuthUtility.getAuthorizeRequest(request));
        map.put("error", error);
        return "signInG";
    }
}
