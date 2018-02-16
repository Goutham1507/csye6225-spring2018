package edu.neu.coe.csye6225.cloudnativeapp.service;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class AmazonClient {

    private AmazonS3 s3Client;

    @Value("${amazonProperties.bucketName:s3.csye6225-spring2018-guptaanu.me}")
    private String bucketName;

    private static final String PROFILE_DIR = "Profile_Pics/";

    @PostConstruct
    private void initializeAmazon() {


        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();


    }


    public void uploadFile(MultipartFile file, String fileName) {


        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        s3Client.putObject("s3.csye6225-spring2018-guptaanu.me", PROFILE_DIR + fileName, inputStream, new ObjectMetadata());

        //s3Client.putObject("s3.csye6225-spring2018-guptaanu.me", PROFILE_DIR + fileName, file);

    }


}
