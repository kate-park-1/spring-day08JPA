package domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "members")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length =45)
  private String username;
  @Enumerated(EnumType.STRING)  // default = ORDINAL
  @Column(nullable = false, length =45)
  private RoleType roleType;
//  @Column(nullable = false)
//  private Long teamId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="team_id")
  private Team team;

  public Member(){}

  public Member(String username, RoleType roleType, Team team) {
    this.username = username;
    this.roleType = roleType;
    this.team = team;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public RoleType getRoleType() {
    return roleType;
  }

  public void setRoleType(RoleType roleType) {
    this.roleType = roleType;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", roleType=" + roleType +
        ", team=" + team +
        '}';
  }
}
