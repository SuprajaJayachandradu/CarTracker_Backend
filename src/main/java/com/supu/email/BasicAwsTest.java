package com.supu.email;

import com.amazonaws.auth.AWSCredentials;
public class BasicAwsTest implements AWSCredentials {

    private final String accessKey;
    private final String secretKey;

    /**
     * Constructs a new BasicAWSCredentials object, with the specified AWS
     * access key and AWS secret key.
     *
     * @param accessKey
     *            The AWS access key.
     * @param secretKey
     *            The AWS secret access key.
     */
    public BasicAwsTest(String accessKey, String secretKey) {
        if (accessKey == null) {
            throw new IllegalArgumentException("Access key cannot be null.");
        }
        if (secretKey == null) {
            throw new IllegalArgumentException("Secret key cannot be null.");
        }

        this.accessKey = "AKIAJGRP3F2DWQSYZRHA";
        this.secretKey = "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";
    }

    /* (non-Javadoc)
     * @see com.amazonaws.auth.AWSCredentials#getAWSAccessKeyId()
     */
    public String getAWSAccessKeyId() {
        return accessKey;
    }

    /* (non-Javadoc)
     * @see com.amazonaws.auth.AWSCredentials#getAWSSecretKey()
     */
    public String getAWSSecretKey() {
        return secretKey;
    }

}
