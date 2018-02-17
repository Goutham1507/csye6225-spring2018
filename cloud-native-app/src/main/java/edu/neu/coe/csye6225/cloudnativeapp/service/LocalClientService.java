package edu.neu.coe.csye6225.cloudnativeapp.service;


import edu.neu.coe.csye6225.cloudnativeapp.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
@Profile("!dev")
public class LocalClientService implements UploadClient {


    @Autowired
    private SecurityServiceImpl securityService;

    private static final String FILE_NAME_PRE = "profile_pic_";

    private static final String DOT = ".";

    private static final String PROFILE_PIC_DIR = "/Profile_Pics/";


    public void storeProfilePic(MultipartFile file) {


        UserAccount loggedInUsername = securityService.findLoggedInUsername();
        String[] split = file.getOriginalFilename().split("\\.");
        String contentType = Arrays.asList(split).get(1);
        String id = loggedInUsername.getId().toString();
        String fileName = FILE_NAME_PRE + id + DOT + contentType;

        String profileDir = System.getProperty("user.dir") + PROFILE_PIC_DIR;

        if (!Files.exists(Paths.get(profileDir))) {

            new File(profileDir).mkdir();

        }
        File tempFile = new File(profileDir + fileName);
        try {
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            File profileImage = convertMultiPartToFile(file, fileName);
//            awsClient.uploadFile(profileImage, fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//
//        try {
//            // read and write the file to the selected location-
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(tempDirectory + "profile_pic_" + id);
//            Files.write(path, bytes);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }


//    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//
//        File convFile = new File(fileName);
//        file.transferTo(convFile);
//        convFile.createNewFile();
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }


    public InputStream getProfilePic() { return null;}
}
