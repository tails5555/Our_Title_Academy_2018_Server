package io.kang.repository.mysql;

import io.kang.domain.mysql.TitleEmpathy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TitleEmpathyRepository extends EmpathyBaseRepository<TitleEmpathy>, JpaRepository<TitleEmpathy, Long> {

}
