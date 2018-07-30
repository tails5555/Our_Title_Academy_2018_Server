package io.kang.repository.mysql;

import io.kang.domain.mysql.Request;
import io.kang.domain.mysql.RequestEmpathy;
import io.kang.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RequestEmpathyRepository extends EmpathyBaseRepository<RequestEmpathy>, JpaRepository<RequestEmpathy, Long> {
    public boolean existsByUserIdAndRequest(String userId, Request request);
    public void deleteByUserIdAndRequest(String userId, Request request);
    public long countByRequestAndStatus(Request request, Status status);

}
