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
        return "admin/dashboard"; // view
    }

    @GetMapping("/card")
    public String card(Model model) {
        return "card"; //view
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
