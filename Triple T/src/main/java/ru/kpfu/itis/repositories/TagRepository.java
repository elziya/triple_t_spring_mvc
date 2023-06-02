package ru.kpfu.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.models.Tag;

import java.util.Optional;
import java.util.Set;

public interface TagRepository extends CrudRepository<Tag, Long> {

    Set<Tag> findAllByAccount_Id(Long id);

    Optional<Tag> findByTagNameAndAccount_Email(String name, String email);
}
