package study.datajpa.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dtos.MemberDTO;
import study.datajpa.entities.Member;
import study.datajpa.repositories.MemberRepository;

import javax.annotation.PostConstruct;

/**
 * Created by finrir on 2019-12-16
 * Description:
 */
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository repository;

    @GetMapping("/member/{id}")
    public String getMember(@PathVariable("id") Long id) {
        return repository.findById(id).get().getName();
    }

    // 도메인 클래스 컨버터
    @GetMapping("/member2/{id}")
    public String getMember(@PathVariable("id") Member member) {
        return member.getName();
    }

    // 페이징 및 정렬
    @GetMapping("")
    public Page<Member> getList(Pageable pageable) {
        Page<Member> page = repository.findAll(pageable);
        return page;
    }

    @GetMapping("/inparam")
    public Page<Member> getListInparam(@PageableDefault(size = 5, sort = "age") Pageable pageable) {
        Page<Member> page = repository.findAll(pageable);
        return page;
    }

    // 엔티티를 DTO 객체로 변환(외부 API 등 스펙 변경 금지용)
    @GetMapping("/toDTO")
    public Page<MemberDTO> convertToDTO(Pageable pageable) {
        Page<Member> members = repository.findAll(pageable);
        return members.map(MemberDTO::new);
    }

//    @PostConstruct
//    public void init() {
//        for (int i = 0; i < 100; i++) {
//            repository.save(new Member("memb" + i, i));
//        }
//    }
}
