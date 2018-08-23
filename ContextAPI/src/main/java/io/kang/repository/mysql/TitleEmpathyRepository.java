package io.kang.repository.mysql;

import io.kang.domain.mysql.Title;
import io.kang.domain.mysql.TitleEmpathy;
import io.kang.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitleEmpathyRepository extends EmpathyBaseRepository<TitleEmpathy>, JpaRepository<TitleEmpathy, Long> {
    public Optional<TitleEmpathy> findByUserIdAndTitle(String userId, Title title);
    public boolean existsByUserIdAndTitle(String userId, Title title);
    public boolean existsByUserIdAndTitleAndStatus(String userId, Title title, Status status);
    public void deleteByUserIdAndTitle(String userId, Title title);
    public long countByTitleAndStatus(Title title, Status status);
}
