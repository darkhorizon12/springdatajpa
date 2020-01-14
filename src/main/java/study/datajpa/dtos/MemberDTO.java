package study.datajpa.dtos;

import lombok.*;
import org.springframework.util.ObjectUtils;
import study.datajpa.entities.Member;

/**
 * Created by finrir on 2019-12-13
 * Description:
 */
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "name", "teamName"})
public class MemberDTO {
    private Long id;
    private String name;
    private String teamName;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.teamName = ObjectUtils.isEmpty(member.getTeam()) ? "" : member.getTeam().getName();
    }
}
