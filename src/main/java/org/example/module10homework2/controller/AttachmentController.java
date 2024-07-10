package org.example.module10homework2.controller;

import lombok.RequiredArgsConstructor;
import org.example.module10homework2.entity.AttachmentContent;
import org.example.module10homework2.repo.AttachmentContentRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentContentRepository attachmentContentRepository;

    @GetMapping("/images/{id}.jpg")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        AttachmentContent attachmentContent = attachmentContentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".jpg\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(attachmentContent.getContent());
    }
}
