package edu.neu.coe.csye6225.cloudnativeapp.controller;



import edu.neu.coe.csye6225.cloudnativeapp.User.ProfileInformation;
import edu.neu.coe.csye6225.cloudnativeapp.domain.UserAccount;
import edu.neu.coe.csye6225.cloudnativeapp.service.SecurityServiceImpl;
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

    @Autowired
    private SecurityServiceImpl securityService;


    @RequestMapping("/createAccount")
    public String home(Model model) {
        model.addAttribute("user", new edu.neu.coe.csye6225.cloudnativeapp.User.UserAccount());
        return "CreateAccount";
    }


    @RequestMapping("/")
    public String profile(Model model){

        UserAccount loggedInUser = securityService.findLoggedInUsername();

        model.addAttribute("profileInfo", loggedInUser);
        return "ProfileDashboard";


    }

  /*  @RequestMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("profileInfo", getProfileInfo());
        return "ProfileDashboard";
    }*/

    private ProfileInformation getProfileInfo(UserAccount ua) {
        ProfileInformation pi = new ProfileInformation();
        pi.setFirstName(ua.getFirstName());
        pi.setLastName(ua.getLastName());
        pi.setEmailId(ua.getEmailAddress());
        pi.setTimestamp(new Date());
        return pi;
    }

    @PostMapping("/createAccount")
    public String createAccount(Model model, @ModelAttribute UserAccount user) {

        String originalPwd = user.getPassword();
        userService.save(user);
        securityService.autologin(user.getEmailAddress(),originalPwd);
        model.addAttribute("profileInfo", user);
        return "ProfileDashboard";

    }




}

