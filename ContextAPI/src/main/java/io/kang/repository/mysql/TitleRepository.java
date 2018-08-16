package io.kang.repository.mysql;

import io.kang.domain.mysql.Category;
import io.kang.domain.mysql.Request;
import io.kang.domain.mysql.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    @Query(value = "SELECT t.* FROM Title t LEFT JOIN TitleEmpathy te ON t.id = te.titleId LEFT JOIN Empathy e ON te.id = e.id WHERE t.requestId = :id AND e.status = 'LIKE' GROUP BY t.id ORDER BY COUNT(te.id) DESC LIMIT 1", nativeQuery = true)
    public Optional<Title> findTopByRequestIdOrderByLikeCountDesc(@Param("id") long id); // 요청 목록을 불러올 때 제목 공감 수가 제일 높은 제목을 가져올 때 이용
    @Query(value = "SELECT t.* FROM Title t LEFT JOIN TitleEmpathy te ON t.id = te.titleId LEFT JOIN Empathy e ON te.id = e.id WHERE t.requestId = :id AND e.status = 'LIKE' GROUP BY t.id ORDER BY COUNT(te.id) DESC LIMIT 5", nativeQuery = true)
    public List<Title> findTop5ByRequestIdOrderByLikeCountDesc(@Param("id") long id); // 요청 상세 내용을 불러올 때 명예의 전당을 가져올 때 이용
    public List<Title> findAllByOrderByWrittenDateDesc(); // ADMIN 권한을 가진 사람이 현재까지 모두 작성한 제목 목록을 가져올 때 이용하는 함수
    public List<Title> findByUserIdOrderByWrittenDateDesc(String userId); // 회원 별 제목 통계를 낼 때 쓰는 제목 목록에 이용
    public List<Title> findByRequestOrderByWrittenDateDesc(Request request); // 요청 안에 있는 제목 목록을 최신 순으로 가져옴
    public Optional<Title> findByUserIdAndRequest(String userId, Request request); // 모든 회원이 한 요청 당 하나의 제목을 달 수 있어 사용자 ID와 요청으로 탐색한다.
    public List<Title> findByUserIdAndRequestCategory(String userId, Category category); // 회원 별 제목 통계를 낼 때 이용한다. 제목 자체를 가져오는 이유가 공감 통계를 포함하기 위한 목적이 있다.
    public List<Title> findByContextContains(String keyword); // 제목 안의 단어로 검색하는 기능

    public long countByRequest(Request request); // 요청 안에 있는 제목 수를 추출한다.
}
