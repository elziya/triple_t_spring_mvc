package ru.kpfu.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "file_info")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long size;

    @Column(name = "content_type", nullable = false, columnDefinition = "VARCHAR(200)")
    private String contentType;

    @Column(name = "orig_name", nullable = false, columnDefinition = "VARCHAR(200)")
    private String originalName;

    @Column(name = "storage_name", nullable = false, columnDefinition = "VARCHAR(200)")
    private String storageFileName;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
