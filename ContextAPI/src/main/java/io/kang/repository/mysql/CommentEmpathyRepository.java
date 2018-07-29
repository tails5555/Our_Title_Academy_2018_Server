package io.kang.repository.mysql;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentEmpathyRepository extends EmpathyBaseRepository<CommentEmpathy>, JpaRepository<CommentEmpathy, Long> {

}
