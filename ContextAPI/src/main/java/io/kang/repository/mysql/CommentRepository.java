package io.kang.repository.mysql;

import io.kang.domain.mysql.Comment;
import io.kang.domain.mysql.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByUserIdOrderByWrittenDateDesc(String userId);
    public List<Comment> findByRequestOrderByWrittenDateDesc(Request request);
    public long countByRequest(Request request);
}
