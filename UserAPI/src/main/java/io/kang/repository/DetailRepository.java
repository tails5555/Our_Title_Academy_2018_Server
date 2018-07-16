package io.kang.repository;

import io.kang.domain.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    public Optional<Detail> findByUserLoginId(String loginId);
}
