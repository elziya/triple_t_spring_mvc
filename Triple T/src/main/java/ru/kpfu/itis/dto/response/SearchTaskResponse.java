package ru.kpfu.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.Task;
import ru.kpfu.itis.utils.converters.DateConverter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchTaskResponse {

    private Long id;

    private String name;

    private Integer duration;

    private Set<TagResponse> tags;

    private ProjectResponse project;

    public static SearchTaskResponse from(Task task, DateConverter dateConverter) {
        return SearchTaskResponse.builder()
                .id(task.getId())
                .name(task.getName())
                .duration(task.getDuration())
                .project(ProjectResponse.from(task.getProject(), dateConverter))
                .tags(task.getTags() == null ? null : TagResponse.from(task.getTags()))
                //.files(FileInfoResponse.from(task.getFiles()))
                .build();
    }

    public static List<SearchTaskResponse> from(List<Task> tasks, DateConverter dateConverter) {
        return tasks.stream().map(task -> from(task, dateConverter)).collect(Collectors.toList());
    }
}
