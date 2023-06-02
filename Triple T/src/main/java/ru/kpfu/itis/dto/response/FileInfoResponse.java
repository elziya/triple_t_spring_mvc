package ru.kpfu.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.FileInfo;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfoResponse {

    private Long id;

    private String originalName;

    public static FileInfoResponse from(FileInfo fileInfo) {
        return FileInfoResponse.builder()
                .id(fileInfo.getId())
                .originalName(fileInfo.getOriginalName())
                .build();
    }

    public static List<FileInfoResponse> from(List<FileInfo> fileInfos) {
        return fileInfos.stream().map(FileInfoResponse::from).collect(Collectors.toList());
    }
}
