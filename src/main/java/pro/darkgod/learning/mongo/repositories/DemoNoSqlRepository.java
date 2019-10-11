package pro.darkgod.learning.mongo.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pro.darkgod.learning.mongo.models.DemoDocument;

@Repository
public interface DemoNoSqlRepository extends MongoRepository<DemoDocument, String> {

    Optional<DemoDocument> findByUsername(String username);

    @Query("{ username: { $regex: '^?0', $options: 'i' } }")
    Page<DemoDocument> findByUsernameFragment(String username, Pageable pageable);
}
