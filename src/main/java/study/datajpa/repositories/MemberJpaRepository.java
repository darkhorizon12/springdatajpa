package study.datajpa.repositories;

import org.springframework.stereotype.Repository;
import study.datajpa.entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Created by finrir on 2019-12-12
 * Description:
 */
@Repository
public class MemberJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age = :age order by m.id desc", Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long totalCount(int age) {
        return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    public int bulkAgeUpdate(int cnt, int target) {
        return em.createQuery("update Member m set m.age = m.age + :cnt where m.age > :target")
                .setParameter("cnt", cnt)
                .setParameter("target", target)
                .executeUpdate();
    }

    public List<Member> findMembersByJoinFetch() {
        return em.createQuery("select m from Member m left join fetch m.team", Member.class)
                .getResultList();
    }
}
