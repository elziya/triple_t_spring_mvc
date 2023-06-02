package ru.kpfu.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.Project;
import ru.kpfu.itis.utils.converters.DateConverter;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {

    private Long id;

    private String name;

    private Integer duration;

    private String startDate;

    private String endDate;

    private List<TaskResponse> tasks;

    public static ProjectResponse from(Project project, DateConverter dateConverter) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .duration(project.getDuration() == null ? 0 : project.getDuration())
                .startDate(dateConverter.convert(project.getStartDate()))
                .endDate(dateConverter.convert(project.getEndDate()))
                .tasks(project.getTasks() == null ? null : TaskResponse.from(project.getTasks()))
                .build();
    }

    public static List<ProjectResponse> from(List<Project> projects, DateConverter dateConverter) {
        return projects.stream().map(project -> from(project, dateConverter)).collect(Collectors.toList());
    }
}
