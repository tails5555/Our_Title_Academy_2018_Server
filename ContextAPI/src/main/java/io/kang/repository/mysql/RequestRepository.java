package io.kang.repository.mysql;

import io.kang.domain.mysql.Category;
import io.kang.domain.mysql.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    public List<Request> findByCategory(Category category);
    public List<Request> findByCategoryIsNull();
}
