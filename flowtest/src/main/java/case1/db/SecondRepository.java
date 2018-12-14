package case1.db;

import case1.core.First;
import case1.core.Second;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Table;

@Table(name = "second")
public interface SecondRepository extends JpaRepository<Second, Long> {
    @Query(value = "SELECT * FROM second WHERE second.uuid = :uuid", nativeQuery = true)
    Second findByUuid(@Param("uuid") String uuid);

//    @Query(value = "SELECT count(*) FROM second", nativeQuery = true)
//    Long count();

}

