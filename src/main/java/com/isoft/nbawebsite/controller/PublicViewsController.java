package com.isoft.nbawebsite.controller;

import com.isoft.nbawebsite.constants.ContentType;
import com.isoft.nbawebsite.content.ContentService;
import com.isoft.nbawebsite.user.User;
import com.isoft.nbawebsite.user.UserService;
import com.isoft.nbawebsite.user.command.NameSearchCmd;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicViewsController {

    private final UserService userService;
    private final ContentService contentService;

    public PublicViewsController(UserService userService, ContentService contentService) {
        this.userService = userService;
        this.contentService = contentService;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("featuredEvents", contentService.findAllContentTypes(ContentType.EVENTS));//.subList(0, 3));
        model.addAttribute("latestNews", contentService.findAllContentTypes(ContentType.NEWS));//.subList(0, 2));
        return "index"; //view
    }

    @GetMapping("/layout")
    public String layout(Model model) {
        return "layout"; //view
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about"; //view
    }

    @GetMapping("/events")
    public String events(Model model) {
        model.addAttribute("events", contentService.findAllContentTypes(ContentType.EVENTS));
        return "events"; //view
    }

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("news", contentService.findAllContentTypes(ContentType.NEWS));
        return "news"; //view
    }

    @GetMapping("/president")
    public String president(Model model) {
        return "president"; //view
    }

    @GetMapping("/findmember")
    public String findmember(Model model) {
        return "findmember"; //view
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "catalogue";
    }

    @GetMapping("/individualprofile/{id}")
    public String indprofile(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "indprofile"; //view
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login"; //view
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup"; //view
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "authorized/dashboard"; //view
    }

    @GetMapping("/library")
    public String library(Model model) {
        return "authorized/library"; //view
    }

    @GetMapping("/meetingroom")
    public String meetingroom(Model model) {
        return "authorized/meetingroom"; //view
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        return "authorized/payment"; //view
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        return "authorized/settings"; //view
    }

    @GetMapping("/admindash")
    public String admindash(Model model) {
        return "authorized/admindash"; //view
    }


    @GetMapping("/admindashboard")
    public String admindashboard(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "admin/dashboard"; //view
    }

    @GetMapping("/card")
    public String card(Model model) {
        return "card"; //view
    }

    @PostMapping("/search")
    public String findLawyersByFullName(String fullName, Model model) {
        NameSearchCmd cmd = new NameSearchCmd();
        cmd.setFirstName(fullName.split("")[0]);
        cmd.setLastName(fullName.split("")[1]);
        model.addAttribute("users", userService.findByFirstNameOrLastName(cmd));
        return "catalogue";
    }

    @GetMapping("/pendingprofile")
    public String pendingprofile(Model model) {
        return "admin/pendingprofile"; //view
    }

    @GetMapping("/normaldashboard")
    public String normaldashboard(Model model) {
        return "admin/normaldashboard"; //view
    }

    @GetMapping("/pendingrequest")
    public String pendingrequest(Model model) {
        return "admin/pendingrequest"; //view
    }

    @GetMapping("/normalpost")
    public String normalpost(Model model) {
        return "admin/normalpost"; //view
    }

    @GetMapping("/memberlist")
    public String memberlist(Model model) {
        return "admin/memberlist"; //view
    }
}
