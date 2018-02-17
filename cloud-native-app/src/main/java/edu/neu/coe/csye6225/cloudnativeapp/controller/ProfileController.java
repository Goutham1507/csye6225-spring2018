package edu.neu.coe.csye6225.cloudnativeapp.controller;


import com.amazonaws.util.IOUtils;
import edu.neu.coe.csye6225.cloudnativeapp.service.UploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

import java.io.InputStream;

@Controller
public class ProfileController {


    @Autowired
    public UploadClient uploadClient;

    @PostMapping("/upload")
    public RedirectView uploadProfilePic(@RequestParam("profile-pic") MultipartFile file) {

        uploadClient.storeProfilePic(file);
        RedirectView rv = new RedirectView();
        rv.setUrl("/");
        return rv;


    }


    @RequestMapping(value = "/profilePic", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody
    byte[] getImageWithMediaType() throws IOException {
        InputStream is = uploadClient.getProfilePic();
        if(is != null) {
            return IOUtils.toByteArray(is);
        }

        ClassPathResource imageFile = new ClassPathResource("static/images/default-placeholder.png");
        return IOUtils.toByteArray(imageFile.getInputStream());

    }

    @RequestMapping(value = "/deletePic", method = RequestMethod.DELETE)
    public
    RedirectView deleteProfilePic() {
        uploadClient.deleteProfilePic();
        RedirectView rv = new RedirectView();
        rv.setUrl("/");
        return rv;

    }




}
