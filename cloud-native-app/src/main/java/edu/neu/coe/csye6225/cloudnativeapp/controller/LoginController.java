package edu.neu.coe.csye6225.cloudnativeapp.controller;



import edu.neu.coe.csye6225.cloudnativeapp.domain.UserAccount;
import edu.neu.coe.csye6225.cloudnativeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new edu.neu.coe.csye6225.cloudnativeapp.User.UserAccount());
        return "CreateAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute UserAccount user) {

        System.out.println(user);
        userService.save(user);

        return "success";
    }


}

