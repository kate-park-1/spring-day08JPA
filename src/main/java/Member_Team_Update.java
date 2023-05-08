import domain.Member;
import domain.RoleType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Member_Team_Update {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("객체 연관관계 수정 : 트랜잭션 시작");

    try {
      tx.begin();
      Team team = new Team();
      team.setName("teamC");
      em.persist(team);

      Member member = em.find(Member.class, 2L);
      member.setTeam(team);

      System.out.println(member);

      System.out.println("영속 상태 --------");
//      Member member1 = em.find(Member.class, member.getId());
//      Team team1 = em.find(Team.class, member.getTeam().getId());
//      System.out.println("member == member1 ? " + member.equals(member1));
//      System.out.println("team == team1 ? " + team.equals(team1));
      System.out.println("커밋 전 --------");
      tx.commit();
      System.out.println("커밋 후 --------");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }
}
