package com.kh.avengers.travels.controller;

import com.kh.avengers.common.dto.RequestData;
import com.kh.avengers.travels.model.service.S3Service;
import com.kh.avengers.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class S3UploadController {

    private final S3Service s3Service;
    private final ResponseUtil responseUtil;

    @PostMapping(value = "/s3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RequestData> uploadToS3(
            @RequestParam("files") List<MultipartFile> files
    ) {
        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    responseUtil.rd("400", null, "업로드할 파일이 없습니다."));
        }

        List<String> uploadedUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = s3Service.upload(file);
            uploadedUrls.add(url);
        }

        return ResponseEntity.ok(responseUtil.rd("200", uploadedUrls, "S3 업로드 성공"));
    }
}
