package io.kang.repository.mysql;

import io.kang.domain.mysql.Comment;
import io.kang.domain.mysql.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByUserIdOrderByWrittenDateDesc(String userId); // 댓글 통계 기능에 대한 구현 여부가 없기 때문에 이는 일단 보류.
    public List<Comment> findByRequestOrderByWrittenDateDesc(Request request); // 한 요청 안에 있는 댓글 목록을 최신 순으로 가져옴
    public long countByRequest(Request request); // 한 요청 안에 있는 댓글 수를 가져옴.
}
