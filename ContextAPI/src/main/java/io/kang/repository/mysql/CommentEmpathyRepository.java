package io.kang.repository.mysql;

import io.kang.domain.mysql.Comment;
import io.kang.domain.mysql.CommentEmpathy;
import io.kang.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentEmpathyRepository extends EmpathyBaseRepository<CommentEmpathy>, JpaRepository<CommentEmpathy, Long> {
    public boolean existsByUserIdAndComment(String userId, Comment comment);
    public void deleteByUserIdAndComment(String userId, Comment comment);
    public long countByCommentAndStatus(Comment comment, Status status);
}
