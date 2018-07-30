package io.kang.repository.mysql;

import io.kang.domain.mysql.Request;
import io.kang.domain.mysql.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    public List<Title> findByUserId(String userId);
    public List<Title> findByRequest(Request request);
}
