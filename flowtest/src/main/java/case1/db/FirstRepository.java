package case1.db;

import case1.core.First;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Table;

@Table(name = "first")
public interface FirstRepository extends JpaRepository<First, Long> {
    @Query(value = "SELECT * FROM first WHERE first.uuid = :uuid", nativeQuery = true)
    First findByUuid(@Param("uuid") String uuid);
}

