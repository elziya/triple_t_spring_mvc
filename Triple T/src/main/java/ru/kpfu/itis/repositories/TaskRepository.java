package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("select task from Task task inner join task.tags tag where lower(tag.tagName) like lower(:tag_name) " +
            "and tag.account.id = :account_id")
    List<Task> findAllByTagLike(@Param("tag_name") String tagName, @Param("account_id") Long accountId);

    Optional<Task> findByIdAndProject_Id(Long taskId, Long projectId);
}