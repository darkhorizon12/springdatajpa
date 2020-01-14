package study.datajpa.repositories;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import study.datajpa.dtos.MemberDTO;
import study.datajpa.entities.Member;
import study.datajpa.entities.QMember;
import study.datajpa.entities.QTeam;

import java.util.List;

import static study.datajpa.entities.QMember.member;

/**
 * Created by finrir on 2019-12-16
 * Description:
 */
@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Member.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Member> findByNameStartsWith(String name) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.name.startsWith(name))
                .fetch();
    }

    // https://stackoverflow.com/questions/36542833/querydsl-group-by-hours-in-a-time-range
    public List<String> convertToDate(Long id) {
        StringTemplate dataPath = Expressions.stringTemplate("date_format({0}, '{1s}')", member.createdDate, ConstantImpl.create("%Y-%m-%d %H:%i:%s"));
        return jpaQueryFactory
                .select(dataPath.as("createDate"))
                .from(member)
                .where(member.id.lt(id))
                .fetch();
    }

    // QueryDSL Fetch Join
    public List<Member> fetchJoin(Long id) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.id.lt(id))
                .leftJoin(member.team)
                .fetchJoin()
                .fetch();
    }

    public List<MemberDTO> injectToDTO(Long id) {
        return null;
    }
}
