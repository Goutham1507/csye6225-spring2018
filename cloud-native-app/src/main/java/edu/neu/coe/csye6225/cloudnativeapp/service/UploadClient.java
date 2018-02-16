package edu.neu.coe.csye6225.cloudnativeapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadClient {


    void storeProfilePic(MultipartFile file);
}
