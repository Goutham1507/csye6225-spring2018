package edu.neu.coe.csye6225.cloudnativeapp.service;


import edu.neu.coe.csye6225.cloudnativeapp.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
public class ProfileService {


    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    private AmazonClient awsClient;


    private static final String FILE_NAME_PRE = "profile_pic_";

    private static final String DOT = ".";


    public void storeProfilePic(MultipartFile file) {


        UserAccount loggedInUsername = securityService.findLoggedInUsername();


        String[] split = file.getOriginalFilename().split("\\.");
        String contentType = Arrays.asList(split).get(1);
        String id = loggedInUsername.getId().toString();

        final String tempDirectory = System.getProperty("java.io.tmpdir");


        String fileName = FILE_NAME_PRE + id + DOT + contentType;

        awsClient.uploadFile(file, fileName);

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


    private File convertMultiPartToFile(MultipartFile file, String fileName) throws IOException {

        File convFile = new File(fileName);
        file.transferTo(convFile);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
