package study.datajpa.repositories;

import study.datajpa.entities.Member;

import java.util.List;

public interface MemberCustomRepository {
    List<Member> findMemberCustomByName(String prefix);
}
