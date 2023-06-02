package ru.kpfu.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.exceptions.TripleTDownloadFileException;
import ru.kpfu.itis.exceptions.TripleTFileNotFoundException;
import ru.kpfu.itis.exceptions.TripleTUploadFileException;
import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.Task;
import ru.kpfu.itis.repositories.FileInfoRepository;
import ru.kpfu.itis.security.details.AccountUserDetails;
import ru.kpfu.itis.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;

    @Value("${files.storage.path}")
    private String storagePath;

    @Override
    public void uploadFile(MultipartFile multipart, Task task) {
        try {
            String extension = multipart.getOriginalFilename().substring(multipart.getOriginalFilename().lastIndexOf("."));

            String storageFileName = UUID.randomUUID() + extension;

            FileInfo file = FileInfo.builder()
                    .size(multipart.getSize())
                    .contentType(multipart.getContentType())
                    .originalName(multipart.getOriginalFilename())
                    .storageFileName(storageFileName)
                    .task(task)
                    .build();

            Files.copy(multipart.getInputStream(), Paths.get(storagePath, file.getStorageFileName()));

            fileInfoRepository.save(file);

        } catch (IOException e) {
            throw new TripleTUploadFileException();
        }
    }

    @Override
    public void addFileToResponse(Long fileId, HttpServletResponse response, UserDetails userDetails) {
        FileInfo file = fileInfoRepository
                .findByIdAndAndTask_Project_Account_Id(fileId,((AccountUserDetails)userDetails).getAccount().getId())
                .orElseThrow(TripleTFileNotFoundException::new);

        response.setContentLength(file.getSize().intValue());
        response.setContentType(file.getContentType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalName() + "\"");

        try {

            IOUtils.copy(new FileInputStream(storagePath + "\\" + file.getStorageFileName()), response.getOutputStream());
            response.flushBuffer();

        } catch (IOException e) {
            throw new TripleTDownloadFileException();
        }
    }
}
