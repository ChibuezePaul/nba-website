package com.isoft.nbawebsite.controller;

import com.isoft.nbawebsite.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicViewsController {

    @GetMapping("/")
    public String main(Model model) {
        return "index"; //view
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about"; //view
    }

    @GetMapping("/events")
    public String events(Model model) {
        return "events"; //view
    }

    @GetMapping("/news")
    public String news(Model model) {
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
        return "catalogue"; //view
    }

    @GetMapping("/individualprofile")
    public String indprofile(Model model) {
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

    @GetMapping("/admindash")
    public String admindash(Model model) {
        return "admindash"; //view
    }

    @GetMapping("/card")
    public String card(Model model) {
        return "card"; //view
    }
}
