package study.datajpa.repositories;

import study.datajpa.entities.Member;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by finrir on 2019-12-16
 * Description:
 */
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final EntityManager em;

    public MemberCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Member> findMemberCustomByName(String prefix) {
        return em.createQuery("select m from Member m where m.name = :prefix")
                .setParameter("prefix", prefix)
                .getResultList();
    }
}
