package ru.kpfu.itis.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "tasks")
@Builder
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;

    @Column(columnDefinition = "integer default 0")
    private Integer duration;

    @OneToMany(mappedBy="project", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
