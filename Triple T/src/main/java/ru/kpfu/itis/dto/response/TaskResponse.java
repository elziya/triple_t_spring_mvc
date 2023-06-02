package ru.kpfu.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.Task;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {

    private Long id;

    private String name;

    private Integer duration;

    private Set<TagResponse> tags;

    private Long projectId;

    private List<FileInfoResponse> files;

    public static TaskResponse from(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .name(task.getName())
                .duration(task.getDuration())
                .tags(task.getTags() == null ? null : TagResponse.from(task.getTags()))
                .files(task.getFiles() == null ? null : FileInfoResponse.from(task.getFiles()))
                .projectId(task.getProject().getId())
                .build();
    }

    public static List<TaskResponse> from(List<Task> tasks) {
        return tasks.stream().map(TaskResponse::from).collect(Collectors.toList());
    }
}
