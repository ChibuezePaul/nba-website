package com.isoft.nbawebsite.controller;

import com.isoft.nbawebsite.commons.data.Messages;
import com.isoft.nbawebsite.user.User;
import com.isoft.nbawebsite.user.UserService;
import com.isoft.nbawebsite.user.command.NewUserCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller @Slf4j
public class PrivateViewsController {

    private final UserService userService;
    private final Messages messages;

    public PrivateViewsController(UserService userService, Messages messages) {
        this.userService = userService;
        this.messages = messages;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") @Valid NewUserCmd newUserCmd, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasGlobalErrors ()) {
            log.warn("Error occurred on signup {}", result);
            return new ModelAndView("signup");
        }
        User user = userService.signup(newUserCmd);
        redirectAttributes.addFlashAttribute("message", messages.get("user.added.ok"));
        return new ModelAndView("admindash", "user", user);
    }
}
