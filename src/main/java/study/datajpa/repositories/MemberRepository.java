package study.datajpa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dtos.MemberDTO;
import study.datajpa.dtos.MemberProjections;
import study.datajpa.entities.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
    List<Member> findByNameStartsWithAndAgeGreaterThan(String name, int age);

    @Query("select m from Member m where m.name = :name and m.age = :age")
    List<Member> findMember(@Param("name") String name, @Param("age") int age);

    @Query("select m.name from Member m")
    List<String> findNames();

    @Query("select new study.datajpa.dtos.MemberDTO(m.id, m.name, t.name) from Member m join m.team t")
    List<MemberDTO> findMemberDTOList();

    List<Member> findByNameIn(Collection<String> names);

    // 빈 값이면 빈 컬렉션 반환
    List<Member> findListByName(String name);

    // 빈 값이면 null 반환
    Member findMemberByName(String name);
    Optional<Member> findOptionalByName(String name);

    // 페이징
    Page<Member> findByAge(int age, Pageable pageable);

    @Query("select m from Member m order by m.age desc")
    Page<Member> findByNameStartsWith(String name, Pageable pageable);

    // 벌크연산: @Modifying = executeUpdate()와 동일한 기능을 수행
    // clearAutomatically: 벌크연산 수행 후 영속성 컨텍스트 초기화
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + :cnt where m.age > :target")
    int bulkAgeUpdate(@Param("cnt") int cnt, @Param("target") int target);

    // 페치조인
//    @Query("select m from Member m left join fetch m.team")
//    public List<Member> findMembersByJoinFetch();
//
//    @EntityGraph(attributePaths = {"team"})
//    public List<Member> findByAgeGreaterThan(int age);

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    // 쿼리힌트
    // 이 힌트에서는 readOnly 속성이라, 엔티티를 변경하더라도 스냅샷을 만들지 않음.(변경 감지 안함)
    @QueryHints(
            value = @QueryHint(name = "org.hibernate.readOnly", value = "true")
    )
    Member findReadOnlyByName(String name);

    // 락 걸기
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockedMembersByName(String name);

    // 네이티브 쿼리 사용
    @Query(value = "select * from member where name = ?", nativeQuery = true)
    List<Member> findByNativeQuery(String name);

    // 네이티브 + 프로젝션 쿼리 사용
    @Query(value = "select m.member_id as id, m.name, t.name as teamName " +
            "from member m left join team t on m.team_id = t.team_id",
            countQuery = "select count(*) from member",
            nativeQuery = true)
    Page<MemberProjections> findByNativeProjections(Pageable pageable);
}
