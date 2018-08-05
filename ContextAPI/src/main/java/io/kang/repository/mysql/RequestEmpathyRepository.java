package io.kang.repository.mysql;

import io.kang.domain.mysql.Request;
import io.kang.domain.mysql.RequestEmpathy;
import io.kang.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface RequestEmpathyRepository extends EmpathyBaseRepository<RequestEmpathy>, JpaRepository<RequestEmpathy, Long> {
    public Optional<RequestEmpathy> findByUserIdAndRequest(String userId, Request request);
    public boolean existsByUserIdAndRequest(String userId, Request request);
    public boolean existsByUserIdAndRequestAndStatus(String userId, Request request, Status status);
    public void deleteByUserIdAndRequest(String userId, Request request);
    public long countByRequestAndStatus(Request request, Status status);
}
