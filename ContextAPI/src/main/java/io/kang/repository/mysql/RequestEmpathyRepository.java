package io.kang.repository.mysql;

import io.kang.domain.mysql.RequestEmpathy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RequestEmpathyRepository extends EmpathyBaseRepository<RequestEmpathy>, JpaRepository<RequestEmpathy, Long> {

}
