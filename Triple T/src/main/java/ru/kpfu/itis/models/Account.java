package ru.kpfu.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(150)")
    private String email;

    @Column(name = "password_hash", columnDefinition = "VARCHAR")
    private String passwordHash;

    @Column(name = "first_name", columnDefinition = "VARCHAR(150)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(150)")
    private String lastName;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy="account")
    private List<Project> projects;

    @OneToMany(mappedBy="account")
    private List<Tag> tags;

    public enum Role {
        USER, ADMIN
    };
}
