package com.isoft.nbawebsite.controller;

import com.isoft.nbawebsite.commons.data.Messages;
import com.isoft.nbawebsite.meeting.MeetingService;
import com.isoft.nbawebsite.user.User;
import com.isoft.nbawebsite.user.UserService;
import com.isoft.nbawebsite.user.command.NewUserCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller @Slf4j
public class PrivateViewsController {

    private final UserService userService;
    private final MeetingService meetingService;
    private final Messages messages;

    public PrivateViewsController(UserService userService, MeetingService meetingService, Messages messages) {
        this.userService = userService;
        this.meetingService = meetingService;
        this.messages = messages;
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Valid NewUserCmd newUserCmd, BindingResult result, Model model){
        if (result.hasGlobalErrors ()) {
            log.warn("Error occurred on signup {}", result);
            return "signup";
        }
        User user = userService.signup(newUserCmd);
        model.addAttribute("user", user);
        model.addAttribute("meetings", meetingService.findByInviteeId(user.getId()));
        return "admindash";
    }
}
