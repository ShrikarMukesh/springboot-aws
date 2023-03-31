package com.aws.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.aws.s3.config.BucketObjectRepresentaion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3BucketObjectsService {

    Logger logger= LoggerFactory.getLogger(S3BucketObjectsService.class);

    private final AmazonS3 amazonS3Client;

    public void downloadObject(String bucketName) throws IOException {

        S3Object s3object = amazonS3Client.getObject(bucketName, "mypic.jpg");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File("/home/shrikar/AWS/mypic.jpg"));
    }

    public List<S3ObjectSummary> listObjects(String bucketName) {
        ObjectListing objectListing = amazonS3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }

    public void deleteMultipleObjects(String bucketName, List<String> objects) {
        DeleteObjectsRequest delObjectsRequests = new DeleteObjectsRequest(bucketName)
                .withKeys(objects.toArray(new String[0]));
        amazonS3Client.deleteObjects(delObjectsRequests);
    }

    public void deleteObject(String bucketName, String objectName) {
        amazonS3Client.deleteObject(bucketName, objectName);
    }

    //Object level operations
    public void putObject(String bucketName, BucketObjectRepresentaion representation, boolean publicObject) throws IOException {

        String objectName = representation.getObjectName();
        String objectValue = representation.getText();

        File file = new File("." + File.separator + objectName);
        FileWriter fileWriter = new FileWriter(file, false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(objectValue);
        printWriter.flush();
        printWriter.close();

        try {
            if (publicObject) {
                var putObjectRequest = new PutObjectRequest(bucketName, objectName, file).withCannedAcl(CannedAccessControlList.PublicRead);
                amazonS3Client.putObject(putObjectRequest);
            } else {
                var putObjectRequest = new PutObjectRequest(bucketName, objectName, file).withCannedAcl(CannedAccessControlList.Private);
                amazonS3Client.putObject(putObjectRequest);
            }
        } catch (Exception e) {
            log.error("Some error has ocurred.");
        }

    }

    public void moveObject(String bucketSourceName, String objectName, String bucketTargetName) {
        amazonS3Client.copyObject(
                bucketSourceName,
                objectName,
                bucketTargetName,
                objectName
        );
    }
}
