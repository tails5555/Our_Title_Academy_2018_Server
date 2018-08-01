package io.kang.repository.mysql;

import io.kang.domain.mysql.Category;
import io.kang.domain.mysql.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    public List<Request> findAllByOrderByWrittenDateDesc();
    public List<Request> findTop10ByCategoryIsNotNullAndAvailableOrderByWrittenDateDesc(Boolean available);
    public List<Request> findTop10ByCategoryIsNotNullAndAvailableOrderByViewDesc(Boolean available);
    public List<Request> findByUserIdAndCategoryIsNullOrderByWrittenDateDesc(String userId);
    public List<Request> findByUserIdAndCategoryIsNotNullOrderByWrittenDateDesc(String userId);
    public List<Request> findByCategoryOrderByWrittenDateDesc(Category category);
    public List<Request> findByCategoryIsNullOrderByWrittenDateDesc();
}
