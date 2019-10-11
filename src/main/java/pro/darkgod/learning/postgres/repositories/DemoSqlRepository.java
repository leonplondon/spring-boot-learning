package pro.darkgod.learning.postgres.repositories;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.darkgod.learning.postgres.models.DemoEntity;

@Repository
public interface DemoSqlRepository extends CrudRepository<DemoEntity, Long> {

    List<DemoEntity> findAll();

    @Query(
        value = "SELECT COUNT(de) FROM DemoEntity de WHERE de.date >= :date",
        nativeQuery = false
    )
    Integer countEntitiesAfterDate(@Param("date") OffsetDateTime date);
}
