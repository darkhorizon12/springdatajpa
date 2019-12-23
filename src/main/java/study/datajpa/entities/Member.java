package study.datajpa.entities;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;

/**
 * Created by finrir on 2019-12-12
 * Description:
 */
@Entity
@Table(name = "MEMBER")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name", "age"})
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Member(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public void changeTeam(Team team) {
        if (CollectionUtils.isNotEmpty(this.team.getMembers()) &&
                CollectionUtils.containsAny(this.team.getMembers(), this)) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }
}
