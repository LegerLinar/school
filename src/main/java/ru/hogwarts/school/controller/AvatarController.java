package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/avatar")

public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("/{studentId}")
    public void uploadPic(@PathVariable long studentId, @RequestParam MultipartFile file) throws IOException {
        avatarService.uploadPic(studentId, file);
    }

    @GetMapping("/{student_id}")
    public ResponseEntity<byte[]> findAvatar(@PathVariable long student_id) {
        Avatar avatar = avatarService.findAvatar(student_id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping("/file/{student_id}")
    public void findFromBase(@PathVariable long student_id,
                                               HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(student_id);
        try (
                OutputStream os = response.getOutputStream();
                InputStream is = new FileInputStream(avatar.getFilePath())) {
            is.transferTo(os);
        }

        response.setStatus(200);
        response.setContentType(avatar.getMediaType());
        response.setContentLength((int) avatar.getFileSize());


    }
}
