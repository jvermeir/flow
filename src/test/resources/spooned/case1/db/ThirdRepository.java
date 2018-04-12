package case1.db;


@javax.persistence.Table(name = "tab")
public interface ThirdRepository extends org.springframework.data.jpa.repository.JpaRepository<case1.core.Basic, java.lang.Long> {
    @org.springframework.data.jpa.repository.Query(value = "SELECT * FROM tab WHERE tab.id = :Id", nativeQuery = true)
    java.util.List<case1.core.Basic> findAllById(@org.springframework.data.repository.query.Param("Id")
    java.lang.String Id);
}

