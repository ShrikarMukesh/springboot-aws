package com.aws.s3.controller;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.aws.s3.config.BucketObjectRepresentaion;
import com.aws.s3.service.S3BucketObjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/objects")
@RequiredArgsConstructor
public class S3BucketObjectsController {

    private final S3BucketObjectsService s3ObjectService;

    @GetMapping("/list/{bucketname}")
    public List<S3ObjectSummary> objectsList(@PathVariable String bucketname){
        return  s3ObjectService.listObjects(bucketname);
    }

    @GetMapping("/download/{bucketname}")
    public void downloadObject(@PathVariable String bucketname) throws IOException {
        s3ObjectService.downloadObject(bucketname);
    }

    @PostMapping(value = "/{bucketName}/objects")
    public void createObject(@PathVariable String bucketName, @RequestBody BucketObjectRepresentaion representaion) throws IOException {
        s3ObjectService.putObject(bucketName, representaion,true);
    }

    @PatchMapping(value = "/{bucketName}/objects/{objectName}/{bucketSource}")
    public void moveObject(@PathVariable String bucketName, @PathVariable String objectName, @PathVariable String bucketSource) throws IOException {
        s3ObjectService.moveObject(bucketName, objectName, bucketSource);
    }

}
