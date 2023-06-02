package ru.kpfu.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.dto.request.SignUpForm;
import ru.kpfu.itis.exceptions.TripleTDuplicateEmailException;
import ru.kpfu.itis.services.SignUpService;
import ru.kpfu.itis.utils.VkOAuthUtility;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final SignUpService signUpService;
    private final VkOAuthUtility vkOAuthUtility;

    @PreAuthorize("isAnonymous()")
    @GetMapping
    public String getSignUpPage(ModelMap map, HttpServletRequest request){
        map.put("vkRequest", vkOAuthUtility.getAuthorizeRequest(request));
        return "signUpG";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
            return "signUpG";
        }

        try {
            signUpService.signUp(form);
        } catch (TripleTDuplicateEmailException e) {
            model.addAttribute("errors", Collections.singleton(e.getMessage()));
            return "signUpG";
        }
        return  "redirect:" + MvcUriComponentsBuilder.fromMappingName("SIC#getSignInPage").build();
    }
}
