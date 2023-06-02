package ru.kpfu.itis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.kpfu.itis.dto.response.ExceptionDto;
import ru.kpfu.itis.exceptions.TripleTRestDuplicateEntityException;
import ru.kpfu.itis.exceptions.TripleTRestNotFoundException;
import ru.kpfu.itis.exceptions.TripleTServiceException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(TripleTRestNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDto> onCustomRestNotFoundExceptions(
            TripleTRestNotFoundException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ExceptionDto.builder()
                        .status(exception.getHttpStatus().value())
                        .message(exception.getMessage())
                        .simple(false)
                        .build());
    }

    @ExceptionHandler(TripleTRestDuplicateEntityException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDto> onCustomRestDuplicateEntityExceptions(
            TripleTRestDuplicateEntityException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ExceptionDto.builder()
                        .status(exception.getHttpStatus().value())
                        .message(exception.getMessage())
                        .simple(true)
                        .build());
    }

    @ExceptionHandler(TripleTServiceException.class)
    public ModelAndView onServiceExceptions(TripleTServiceException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());
        mav.addObject("status", exception.getHttpStatus().value());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView onNoHandlerFoundExceptions(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Page not found");
        mav.addObject("status", HttpStatus.NOT_FOUND.value());
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView onAllExceptions(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());

        if (exception.getClass() == AccessDeniedException.class){
            mav.addObject("status", HttpStatus.FORBIDDEN.value());
        }
        else {
            mav.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        mav.setViewName("error/error");
        return mav;
    }
}
