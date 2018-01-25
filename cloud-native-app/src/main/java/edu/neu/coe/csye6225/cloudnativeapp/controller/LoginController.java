package edu.neu.coe.csye6225.cloudnativeapp.controller;



import edu.neu.coe.csye6225.cloudnativeapp.User.ProfileInformation;
import edu.neu.coe.csye6225.cloudnativeapp.domain.UserAccount;
import edu.neu.coe.csye6225.cloudnativeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new edu.neu.coe.csye6225.cloudnativeapp.User.UserAccount());
        return "CreateAccount";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("profileInfo", getProfileInfo());
        return "ProfileDashboard";
    }

    private ProfileInformation getProfileInfo() {
        ProfileInformation pi = new ProfileInformation();
        pi.setFirstName("Devesh");
        pi.setLastName("Kandpal");
        pi.setTimestamp(new Date());
        return pi;
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute UserAccount user) {

        System.out.println(user);
        userService.save(user);

        return "success";
    }




}

