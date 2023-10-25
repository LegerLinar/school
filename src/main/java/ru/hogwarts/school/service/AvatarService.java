package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exceptions.AvatarNotFoundException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AvatarService {
    private String avatarsDir;
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentRepository studentRepository,
                         AvatarRepository avatarRepository,
                         @Value("${avatars.dir}") String avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    public void uploadPic(long studentId, MultipartFile file) throws IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));


        Path dir = Path.of(avatarsDir);
        if (!dir.toFile().exists()) {
            Files.createDirectories(dir);
        }

        String path = saveFile(file, student);

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath(path);
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatarRepository.save(avatar);
    }


    public Avatar findAvatar(long studentId) {
        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(null);
        if (avatar == null) {
            throw new AvatarNotFoundException();
        }
        return avatar;
    }

    private String saveFile(MultipartFile file, Student student) throws IOException {
        int indexOfDod = file.getOriginalFilename().lastIndexOf('.');
        String fileExtension = file.getOriginalFilename().substring(indexOfDod + 1);
        String path = avatarsDir + "/" + student.getId() + "_" + student.getName() + "." + fileExtension;

        try (
                InputStream is = file.getInputStream();
                OutputStream os = new FileOutputStream(path)) {
            is.transferTo(os);
        }
        return path;
    }
}
