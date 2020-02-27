package io.github.tyman138.task2.fileWriter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public class CustomFileWriter {
    public void writeToFile(MultipartFile multipartFile, String path) {
        if (!multipartFile.isEmpty()) {
            try {
                multipartFile.transferTo(Path.of(path + multipartFile.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
