package io.kang.repository;

import io.kang.domain.Age;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeRepository extends JpaRepository<Age, Long> {

}
