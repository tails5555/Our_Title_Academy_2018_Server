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
    public List<Integer> sizeBy = Arrays.asList(4, 6, 8, 10, 12, 14, 16, 18, 20);
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

    public Page<Request> findByCategoryIdAndAvailableIsTrue(Long categoryId, Pageable pageable); // 페이징네이션의 전체 목록
    public Page<Request> findByCategoryIdAndAvailableIsTrueAndIntroContains(Long categoryId, String intro, Pageable pageable); // 페이징네이션에서 요청 제목으로 탐색
    public Page<Request> findByCategoryIdAndAvailableIsTrueAndContextContains(Long categoryId, String context, Pageable pageable); // 페이징네이션에서 요청 내용으로 탐색
    public Page<Request> findByCategoryIdAndAvailableIsTrueAndUserId(Long categoryId, String userId, Pageable pageable); // 페이징네이션에서 사용자 ID로 탐색

    public List<Request> findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc(); // Home Index 에서 보여줄 최신 목록 10개
    public List<Request> findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByViewDesc(); // 랭킹을 매길 때 쓰는 목록 10개
    public List<Request> findByUserIdAndCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc(String userId); // 사용자 별 요청 통계에서 유효한 요청을 낼 때 이용
    public List<Request> findByUserIdAndAvailableIsFalseOrderByWrittenDateDesc(String userId); // 사용자 별 요청 통계에서 유효하지 않은 요청을 낼 때 이용
    public List<Request> findByUserIdAndCategoryOrderByWrittenDateDesc(String userId, Category category); // 회원 별 요청 통계를 낼 때 이용한다. 제목 자체를 가져오는 이유가 공감 통계를 포함하기 위한 목적이 있다.
    public List<Request> findByCategoryIsNotNullAndAvailableIsTrue(); // 웹 소켓을 이용하여 오늘의 제목 배틀 기능 구현 시 이용한다.
    public List<Request> findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc(); // 차단 당한 요청을 가져올 때 쓰는 함수
    public List<Request> findByCategoryIsNullAndAvailableIsFalseOrderByWrittenDateDesc(); // 새로 올린 요청을 가져올 때 쓰는 함수
    public List<Request> findByIntroContainsOrContextContains(String keyword1, String keyword2); // 통합 검색에서 제목이나 내용으로 탐색할 때 이용
}
