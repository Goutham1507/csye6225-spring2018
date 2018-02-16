package edu.neu.coe.csye6225.cloudnativeapp.controller;


import edu.neu.coe.csye6225.cloudnativeapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;

@Controller
public class ProfileController {


    @Autowired
    public ProfileService profileService;

    @PostMapping("/upload")
    public void uploadProfilePic(@RequestParam("profile-pic")MultipartFile file){


        String contentType = file.getContentType();
        String name = file.getName();

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println("Content Type " + contentType + ",name" + name);
        System.out.println("---------------------------------------------------------------------------------------");

        profileService.storeProfilePic(file);






    }
}
