package com.supu.email;

import java.util.Arrays;
import java.util.List;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
public class AwsPostMan implements PostMan {
    private String from, to, subject, body;
    private AmazonSimpleEmailService simpleEmailService;
    public AwsPostMan(AmazonSimpleEmailService simpleEmailService) {
        this.simpleEmailService = simpleEmailService;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public PostMan withFrom(String from) {
        this.from = from;
        return this;
    }
    public PostMan withTo(String to) {
        this.to = to;
        return this;
    }
    public PostMan withSubject(String subject) {
        this.subject = subject;
        return this;
    }
    public PostMan withBody(String body) {
        this.body = body;
        return this;
    }
    private List<String> getToAsList() {
        return Arrays.asList(to.split(","));
    }
    public void send() {
        Destination destination = new Destination(getToAsList());
        SendEmailRequest request = new SendEmailRequest(from, destination,
                createMessage());
        simpleEmailService.sendEmail(request);
    }
    private Message createMessage() {
        Body amazonBody = new Body(new Content(body));
        Message message = new Message(new Content(subject), amazonBody);
        return message;
    }
}