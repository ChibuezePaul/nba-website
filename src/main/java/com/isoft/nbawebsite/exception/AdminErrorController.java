package com.isoft.nbawebsite.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class AdminErrorController {

	@ExceptionHandler(CustomException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(final CustomException throwable, WebRequest request) {
        if (request != null && request.getUserPrincipal () != null) {
            log.error("Displaying Error Page");
			ModelAndView modelAndView = new ModelAndView("error");
			modelAndView.getModel ().put ( "error", throwable != null ? throwable.getMessage() : "Unknown error" );
            return modelAndView;
        }
        else {
            SecurityContextHolder.getContext().setAuthentication(null);// logout
			log.error("Displaying Login Page");
            return new ModelAndView("login");
        }
    }
}