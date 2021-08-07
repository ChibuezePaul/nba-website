package com.isoft.nbawebsite.controller;

import com.isoft.nbawebsite.constants.AccountStatus;
import com.isoft.nbawebsite.constants.ContentType;
import com.isoft.nbawebsite.content.ContentService;
import com.isoft.nbawebsite.content.command.NewContent;
import com.isoft.nbawebsite.meeting.MeetingService;
import com.isoft.nbawebsite.meeting.command.NewMeeting;
import com.isoft.nbawebsite.payment.PaymentService;
import com.isoft.nbawebsite.user.User;
import com.isoft.nbawebsite.user.UserService;
import com.isoft.nbawebsite.user.command.NameSearchCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.math.BigDecimal;

@Controller @RequiredArgsConstructor
public class ViewsController {

    private final UserService userService;
    private final ContentService contentService;
    private final MeetingService meetingService;
    @Value("${monthly-due-amount}")
    private long monthlyDueAmount;
    private final PaymentService paymentService;


    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("featuredEvents", contentService.findFeaturedEvent());
        model.addAttribute("latestNews", contentService.findRecentPosts());
        return "index"; //view
    }

    @GetMapping("/layout")
    public String layout() {
        return "layout"; //view
    }

    @GetMapping("/about")
    public String about() {
        return "about"; //view
    }

    @GetMapping("/events")
    public String events(Model model) {
        model.addAttribute("events", contentService.findAllContentTypes(ContentType.EVENTS));
        return "events"; //view
    }

    @GetMapping("/viewevents/{id}")
    public String viewEvents(Model model, @PathVariable String id) {
        model.addAttribute("event", contentService.findById(id));
        return "viewevents"; //view
    }

    @GetMapping("/newspage/{id}")
    public String newsPage(Model model, @PathVariable String id) {
        model.addAttribute("news", contentService.findById(id));
        return "newspage"; //view
    }

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("allNews", contentService.findAllContentTypes(ContentType.NEWS));
        return "news"; //view
    }

    @GetMapping("/president")
    public String president() {
        return "president"; //view
    }

    @GetMapping("/findmember")
    public String findMember() {
        return "findmember"; //view
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "catalogue";
    }

    @GetMapping("/individualprofile/{id}")
    public String individualProfile(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "indprofile"; //view
    }

    @GetMapping("/login")
    public String login() {
        return "login"; //view
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword() {
        return "forgotpassword"; //view
    }

//    @GetMapping("/resetpassword")
//    public String resetPassword(Model model) {
//        return "resetpassword"; //view
//    }

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
    public String meetingRoom(Model model) {
        model.addAttribute("pendingMeetings", meetingService.findPendingMeetings());
        return "authorized/meetingroom"; //view
    }

    @GetMapping("/createmeeting")
    public String createMeeting(Model model) {
        model.addAttribute("meeting", new NewMeeting());
        return "authorized/createmeeting"; //view
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        model.addAttribute("email", email);
        model.addAttribute("payments", paymentService.getUserPayments(email));
        return "authorized/payment"; //view
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        return "authorized/settings"; //view
    }

    @GetMapping("/admindash")
    public String adminDashboard(Model model) {
        return "authorized/dashboard"; //view
    }


    @GetMapping("/controlpanel")
    public String controlPanel(Model model) {
        model.addAttribute("pendingRequestCount", userService.findUsersByAccountStatus(AccountStatus.PENDING).size());
        model.addAttribute("approvedRequestCount", userService.findUsersByAccountStatus(AccountStatus.APPROVED).size());
        model.addAttribute("rejectedRequestCount", userService.findUsersByAccountStatus(AccountStatus.REJECTED).size());
        model.addAttribute("recentUsers", userService.findRecentUsers());
        return "admin/controlpanel"; //view
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

    @GetMapping("/pendingprofile/{id}")
    public String pendingProfile(Model model, @PathVariable String id) {
        model.addAttribute("user", userService.findById(id));
        return "admin/pendingprofile"; //view
    }

    @GetMapping("/postsdashboard")
    public String postsDashboard(Model model) {
        model.addAttribute("recentPosts", contentService.findRecentPosts());
        return "admin/postsdashboard"; //view
    }

    @GetMapping("/pendingrequest")
    public String pendingRequest(Model model) {
        model.addAttribute("pendingUsers", userService.findUsersByAccountStatus(AccountStatus.PENDING));
        return "admin/pendingrequest"; //view
    }

    @GetMapping("/rejectedrequest")
    public String rejectedRequest(Model model) {
        model.addAttribute("pendingUsers", userService.findUsersByAccountStatus(AccountStatus.REJECTED));
        return "admin/pendingrequest"; //view
    }

    @GetMapping("/createpost")
    public String createPost(Model model) {
        model.addAttribute("post", new NewContent());
        return "admin/createpost"; //view
    }

    @GetMapping("/editpost/{id}")
    public String editpost(Model model, @PathVariable String id) {
        model.addAttribute("isUpdate", true);
        model.addAttribute("post", contentService.findById(id));
        return "admin/createpost"; //view
    }

    @GetMapping("/createevent")
    public String createEvent(Model model) {
        model.addAttribute("event", new NewContent());
        return "admin/createevents"; //view
    }

    @GetMapping("/editevent/{id}")
    public String editEvent(Model model, @PathVariable String id) {
        model.addAttribute("isUpdate", true);
        model.addAttribute("event", contentService.findById(id));
        return "admin/createevents"; //view
    }

    @GetMapping("/memberlist")
    public String membersList(Model model) {
        model.addAttribute("approvedUsers", userService.findUsersByAccountStatus(AccountStatus.APPROVED));
        return "admin/memberlist"; //view
    }

    @ModelAttribute("monthlyDueAmount")
    public BigDecimal getMonthlyDueAmount() {
        return BigDecimal.valueOf(monthlyDueAmount);
    }
}
