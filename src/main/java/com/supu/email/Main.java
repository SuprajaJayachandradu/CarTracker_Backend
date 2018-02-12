package com.supu.email;

import java.io.IOException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
public class Main {
    private AWSCredentials createAwsCredentials() throws IOException {
        AWSCredentials credentials = new BasicAwsTest("AKIAJGRP3F2DWQSYZRHA", "SLJA+jjNXO7nKudhPKVtZEooNGtvbAC8k/1EZCup");
        return credentials;
    }
    private AmazonSimpleEmailService createSimpleEmailService() throws IOException {
        return new AmazonSimpleEmailServiceClient(createAwsCredentials());
    }
    private void sendTestEmail(String body) throws IOException {
        PostMan postMan = new AwsPostMan(createSimpleEmailService());
        postMan.withFrom("supraja.jayachandradu@gmail.com").withTo("supraja.jayachandradu@gmail.com")
                .withSubject("Email from CarTracker").withBody(
                body).send();
    }
    public void sendNewEmail(String body) throws IOException {
        new Main().sendTestEmail(body);
    }
}