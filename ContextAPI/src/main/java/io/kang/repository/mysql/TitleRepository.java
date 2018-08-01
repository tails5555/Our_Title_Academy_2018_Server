package io.kang.repository.mysql;

import io.kang.domain.mysql.Request;
import io.kang.domain.mysql.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    @Query(value = "SELECT t.* FROM Title t LEFT JOIN TitleEmpathy te ON t.id = te.titleId WHERE t.requestId = :id GROUP BY t.id ORDER BY COUNT(te.id) DESC LIMIT 1", nativeQuery = true)
    public Optional<Title> findTopByRequestIdOrderByLikeCountDesc(@Param("id") long id);
    public List<Title> findAllByOrderByWrittenDateDesc();
    public List<Title> findByUserIdOrderByWrittenDateDesc(String userId);
    public List<Title> findByRequestOrderByWrittenDateDesc(Request request);
    public long countByRequest(Request request);
}
