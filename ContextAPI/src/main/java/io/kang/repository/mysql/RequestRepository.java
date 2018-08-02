package io.kang.repository.mysql;

import io.kang.domain.mysql.Category;
import io.kang.domain.mysql.Request;
import io.kang.model.OptionModel;
import io.kang.model.PaginationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    public List<Integer> sizeBy = Arrays.asList(1, 4, 6, 8, 10, 12, 14, 16, 18, 20);
    public List<OptionModel> searchBy = Arrays.asList(new OptionModel(1L, "요청 제목 검색"), new OptionModel(2L, "요청 내용 검색"), new OptionModel(3L, "사용자 ID 검색"));
    public List<OptionModel> orderBy = Arrays.asList(new OptionModel(1L, "최신순"), new OptionModel(2L, "인기순"), new OptionModel(3L, "요청 제목순"), new OptionModel(4L, "작성자 ID 순"));

    Sort[] sort = {
            new Sort(Sort.Direction.DESC, "writtenDate"),
            new Sort(Sort.Direction.DESC, "view"),
            new Sort(Sort.Direction.ASC, "intro"),
            new Sort(Sort.Direction.ASC, "userId")
    };

    public default List<Request> findAll(PaginationModel paginationModel){
        Pageable pageable = new PageRequest(paginationModel.getPg() - 1, paginationModel.getSz(), sort[(paginationModel.getOb() != 0) ? paginationModel.getOb() - 1 : paginationModel.getOb()]);
        Page<Request> page;
        long categoryId = paginationModel.getId();
        String searchText = paginationModel.getSt();
        switch(paginationModel.getSb()){
            case 1 :
                page = this.findByCategoryIdAndAvailableIsTrueAndIntroContains(categoryId, searchText, pageable);
                break;
            case 2 :
                page = this.findByCategoryIdAndAvailableIsTrueAndContextContains(categoryId, searchText, pageable);
                break;
            case 3 :
                page = this.findByCategoryIdAndAvailableIsTrueAndUserId(categoryId, searchText, pageable);
                break;
            default :
                page = this.findByCategoryIdAndAvailableIsTrue(categoryId, pageable);
                break;
        }
        paginationModel.setRequestCount(page.getTotalElements());
        return page.getContent();
    }

    public Page<Request> findByCategoryIdAndAvailableIsTrue(Long categoryId, Pageable pageable);
    public Page<Request> findByCategoryIdAndAvailableIsTrueAndIntroContains(Long categoryId, String intro, Pageable pageable);
    public Page<Request> findByCategoryIdAndAvailableIsTrueAndContextContains(Long categoryId, String context, Pageable pageable);
    public Page<Request> findByCategoryIdAndAvailableIsTrueAndUserId(Long categoryId, String userId, Pageable pageable);

    public List<Request> findTop10ByCategoryIsNotNullAndAvailableOrderByWrittenDateDesc(Boolean available);
    public List<Request> findTop10ByCategoryIsNotNullAndAvailableOrderByViewDesc(Boolean available);

    public List<Request> findByUserIdAndCategoryIsNullOrderByWrittenDateDesc(String userId);
    public List<Request> findByUserIdAndCategoryIsNotNullOrderByWrittenDateDesc(String userId);
    public List<Request> findByCategoryOrderByWrittenDateDesc(Category category);
    public List<Request> findByCategoryIsNullOrderByWrittenDateDesc();
}
