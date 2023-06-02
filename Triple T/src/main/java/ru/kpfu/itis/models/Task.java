package ru.kpfu.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "tags")
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "integer default 0")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            name = "task_tag")
    private Set<Tag> tags;

    @OneToMany(mappedBy="task", cascade = CascadeType.REMOVE)
    private List<FileInfo> files;
}
