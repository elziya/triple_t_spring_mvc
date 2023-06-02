package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Optional<Project> getProjectById(Long id);

    @Query(nativeQuery = true, value = "select id, name, start_date, end_date, account_id, sum(duration) as duration from (\n" +
"                                                                                  select p.id,\n" +
"                                                                                         p.name,\n" +
"                                                                                         p.start_date,\n" +
"                                                                                         p.end_date,\n" +
"                                                                                         p.account_id,\n" +
"                                                                                         t.duration as duration\n" +
"                                                                                  from project p\n" +
"                                                                                           left join task t on p.id = project_id\n" +
"                                                                                  where account_id = :account_id\n" +
"                                                                              ) t2\n" +
            "group by id, name, start_date, end_date, account_id;")
    List<Project> findAllByAccountIdWithDuration(@Param("account_id") Long accountId);
}
