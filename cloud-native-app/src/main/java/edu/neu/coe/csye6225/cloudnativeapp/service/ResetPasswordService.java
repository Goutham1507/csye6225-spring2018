package edu.neu.coe.csye6225.cloudnativeapp.service;


import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.GetTopicAttributesResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class ResetPasswordService {


    AmazonSNSAsync amazonSNSClient;


    @PostConstruct
    public void initializeSNSClient() {

        this.amazonSNSClient = AmazonSNSAsyncClientBuilder.defaultClient();
    }


    public void sendMessage(String emailId) throws ExecutionException, InterruptedException {


        Future<CreateTopicResult> reset_password = amazonSNSClient.createTopicAsync("reset_password");
        String topicArn = reset_password.get().getTopicArn();
        PublishRequest publishRequest = new PublishRequest(topicArn, emailId);
        Future<PublishResult> publishResultFuture = amazonSNSClient.publishAsync(publishRequest);

        String messageId = publishResultFuture.get().getMessageId();

        System.out.println(messageId);


    }

}
