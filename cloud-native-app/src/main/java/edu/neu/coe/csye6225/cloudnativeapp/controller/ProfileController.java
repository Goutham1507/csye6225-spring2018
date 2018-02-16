package edu.neu.coe.csye6225.cloudnativeapp.controller;


import edu.neu.coe.csye6225.cloudnativeapp.service.LocalClientService;
import edu.neu.coe.csye6225.cloudnativeapp.service.UploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ProfileController {


    @Autowired
    public UploadClient uploadClient;




    @PostMapping("/upload")
    public RedirectView uploadProfilePic(@RequestParam("profile-pic")MultipartFile file){

        uploadClient.storeProfilePic(file);
        RedirectView rv = new RedirectView();
        rv.setUrl("/");
        return rv;


    }
}
