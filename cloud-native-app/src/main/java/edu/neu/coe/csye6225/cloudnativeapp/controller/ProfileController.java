package edu.neu.coe.csye6225.cloudnativeapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfileController {

    @PostMapping("/upload")
    public void uploadProfilePic(@RequestParam("profile-pic")MultipartFile file){


        String contentType = file.getContentType();
        String name = file.getName();


        System.out.println("Content Type " + contentType + ",name" + name);




    }
}
