package com.kh.avengers.travels.model.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Service {
    String upload(MultipartFile file);
    List<String> uploadMultiple(List<MultipartFile> files);
}
