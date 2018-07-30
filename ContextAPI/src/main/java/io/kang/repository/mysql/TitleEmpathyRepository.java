package io.kang.repository.mysql;

import io.kang.domain.mysql.Title;
import io.kang.domain.mysql.TitleEmpathy;
import io.kang.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TitleEmpathyRepository extends EmpathyBaseRepository<TitleEmpathy>, JpaRepository<TitleEmpathy, Long> {
    public boolean existsByUserIdAndTitle(String userId, Title title);
    public void deleteByUserIdAndTitle(String userId, Title title);
    public long countByTitleAndStatus(Title title, Status status);
}
