package com.aws.s3.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.aws.s3.config.BucketObjectRepresentaion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3BucketService {

    private final AmazonS3 amazonS3Client;

    //Bucket level operations
    public void createS3Bucket(String bucketName, boolean publicBucket) {
        if (amazonS3Client.doesBucketExistV2(bucketName)) {
            log.info("Bucket name already in use. Try another name.");
            return;
        }
        if (publicBucket) {
            amazonS3Client.createBucket(bucketName);
        } else {
            amazonS3Client.createBucket(new CreateBucketRequest(bucketName).withCannedAcl(CannedAccessControlList.Private));
        }
    }

    public List<Bucket> listBuckets() {
        return amazonS3Client.listBuckets();
    }

    public void deleteBucket(String bucketName) {
        try {
            amazonS3Client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            log.error(e.getErrorMessage());
            return;
        }
    }

}