package io.kang.repository;

import io.kang.domain.Detail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long> {

}
