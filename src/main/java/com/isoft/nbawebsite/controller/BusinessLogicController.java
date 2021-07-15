package com.isoft.nbawebsite.controller;

import com.isoft.nbawebsite.commons.data.Messages;
import com.isoft.nbawebsite.constants.ContentType;
import com.isoft.nbawebsite.content.ContentService;
import com.isoft.nbawebsite.content.command.NewContent;
import com.isoft.nbawebsite.meeting.MeetingService;
import com.isoft.nbawebsite.meeting.command.NewMeeting;
import com.isoft.nbawebsite.user.User;
import com.isoft.nbawebsite.user.UserService;
import com.isoft.nbawebsite.user.command.NewUserCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@Slf4j
public class BusinessLogicController {

    @Value("${image-directory}")
    private String imageDirectory;
    private final UserService userService;
    private final MeetingService meetingService;
    private final ContentService contentService;
    private final Messages messages;

    public BusinessLogicController(UserService userService, MeetingService meetingService, ContentService contentService, Messages messages) {
        this.userService = userService;
        this.meetingService = meetingService;
        this.contentService = contentService;
        this.messages = messages;
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Valid NewUserCmd newUserCmd, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            log.warn("Error occurred on signup {}", result);
            return "signup";
        }
        User user = userService.signup(newUserCmd);
        model.addAttribute("user", user);
        redirectAttributes.addFlashAttribute("message", "User Account Created Successfully. Kindly Hold Off For Admin Confirmation");
        return "login";
    }

    @PostMapping("/user/suspend/{id}")
    public String suspendUser(@PathVariable String id, String suspensionPeriod, RedirectAttributes redirectAttributes) {
        userService.suspendUser(id, suspensionPeriod);
        redirectAttributes.addFlashAttribute("message", "User Account Suspended For "+suspensionPeriod+" Successfully");
        return "redirect:/controlpanel";
    }

    @GetMapping("/user/reinstate/{id}")
    public String reinstateUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
        userService.reinstateUser(id);
        redirectAttributes.addFlashAttribute("message", "User Account Reinstated Successfully");
        return "redirect:/controlpanel";
    }

    @GetMapping("/user/approve/{id}")
    public String approveUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
        userService.approveNewUserRequest(id);
        redirectAttributes.addFlashAttribute("message", "User Account Approved Successfully");
        return "redirect:/controlpanel";
    }

    @GetMapping("/user/reject/{id}")
    public String rejectUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
        userService.rejectNewUserRequest(id);
        redirectAttributes.addFlashAttribute("message", "User Account Rejected Successfully");
        return "redirect:/controlpanel";
    }

    @PostMapping("/forgotpassword")
    public String forgotPassword(String uniqueId, Model model) {
        userService.forgotPassword(uniqueId);
        model.addAttribute("uniqueId", uniqueId);
        return "resetpassword";
    }

    @PostMapping("/resetpassword")
    public String resetPassword(String uniqueId, String password, String confirmPassword, RedirectAttributes redirectAttributes) {
        userService.resetPassword(uniqueId, password, confirmPassword);
        redirectAttributes.addFlashAttribute("message", "Password Reset Successfully");
        return "redirect:/login";
    }

    @GetMapping(value = "image/{id}/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName, @PathVariable String id) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath().replace(".", "");
        File serverFile = new File(path+imageDirectory + id + "/" + imageName);
        return Files.readAllBytes(serverFile.toPath());
    }

    @PostMapping("/new-post")
    public String createPost(@RequestParam String type, @RequestParam MultipartFile attachment, @ModelAttribute("post") @Valid NewContent content, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            log.warn("Error occurred on post creation {}", result);
            return "admin/createpost";
        }
        if ("news".equals(type)) {
            content.setContentType(ContentType.NEWS);
        }else {
            content.setContentType(ContentType.EVENTS);
        }
        contentService.createContent(content, attachment);
        redirectAttributes.addFlashAttribute("message", type.toUpperCase()+" Created Successfully");
        return "redirect:/postsdashboard";
    }

    @PostMapping("/new-post/{id}")
    public String editPost(@RequestParam String type, @RequestParam MultipartFile attachment, @ModelAttribute("post") @Valid NewContent content, BindingResult result, @PathVariable String id, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            log.warn("Error occurred on post creation {}", result);
            return "news".equals(type) ? "redirect:/editpost/"+id : "redirect:/editevent/"+id;
        }
        contentService.editContent(id, content, attachment);
        redirectAttributes.addFlashAttribute("message", type.toUpperCase()+" Updated Successfully");
        return "redirect:/postsdashboard";
    }

    @PostMapping("/delete-post")
    public String deletePost(String id, RedirectAttributes redirectAttributes) {
        contentService.deleteContent(id);
        redirectAttributes.addFlashAttribute("message", "Post Deleted Successfully");
        return "redirect:/postsdashboard";
    }

    @PostMapping("/new-meeting")
    public String createMeeting(@ModelAttribute("meeting") @Valid NewMeeting meeting, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            log.warn("Error occurred on meeting creation {}", result);
            return "authorized/createmeeting";
        }
        meetingService.createMeeting(meeting);
        redirectAttributes.addFlashAttribute("message", "Meeting Created Successfully");
        return "redirect:/meetingroom";
    }
}