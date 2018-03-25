package edu.neu.coe.csye6225.cloudnativeapp.controller;

import edu.neu.coe.csye6225.cloudnativeapp.User.ResetForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResetController {

    @RequestMapping("/resetPassword")
    public String getResetPasswordPage(Model model) {

        model.addAttribute("resetForm", new ResetForm());
        return "resetPassword";

    }

    @PostMapping("/resetPassword")
    public String generateResetToken(@ModelAttribute ResetForm resetForm) {


        System.out.println(resetForm.getEmailAddress());
        return "resetPassword";


    }


}
