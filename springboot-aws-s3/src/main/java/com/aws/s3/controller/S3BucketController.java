package com.aws.s3.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.aws.s3.config.BucketObjectRepresentaion;
import com.aws.s3.service.S3BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/buckets/")
@RequiredArgsConstructor
public class S3BucketController {

    private final S3BucketService s3Service;

    @PostMapping(value = "/{bucketName}")
    public void createBucket(@PathVariable String bucketName){
        s3Service.createS3Bucket(bucketName,true);
    }

    @GetMapping("/list")
    public List<String> listBuckets(){
        var buckets = s3Service.listBuckets();
        var names = buckets.stream().map(Bucket::getName).collect(Collectors.toList());
        return names;
    }


    @DeleteMapping(value = "/{bucketName}")
    public void deleteBucket(@PathVariable String bucketName){
        s3Service.deleteBucket(bucketName);
    }

//    @DeleteMapping(value = "/many/{bucketName}")
//    public void deleteMultipleObjectsFromBucket(@PathVariable String bucketName, @RequestBody ){
//        s3Service.deleteMultipleObjects(bucketName);
//    }

}