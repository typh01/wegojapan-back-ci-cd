package com.kh.avengers.travels.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.folder:images}")
    private String folder;

    @Override
    public String upload(MultipartFile file) {
        String filename = generateFileName(file.getOriginalFilename());

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(filename)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            return getPublicUrl(filename);

        } catch (IOException e) {
            log.error("S3 업로드 실패", e);
            throw new RuntimeException("S3 업로드 실패", e);
        }
    }

    @Override
    public List<String> uploadMultiple(List<MultipartFile> files) {
        return files.stream()
                .map(this::upload)
                .collect(Collectors.toList());
    }

    private String generateFileName(String original) {
        String uuid = UUID.randomUUID().toString();
        return folder + "/" + uuid + "_" + original.replace(" ", "_");
    }

    private String getPublicUrl(String key) {
        return "https://" + bucket + ".s3.amazonaws.com/" + key;
    }
}
