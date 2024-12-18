package jpa.querydsl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public Member(String username, int age){
        this(username, age, null);
    }
    public Member(String username){
        this(username, 0, null);
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return age == member.age && Objects.equals(id, member.id) && Objects.equals(username, member.username) && Objects.equals(team, member.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, age, team);
    }
}
