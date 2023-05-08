import domain.Member;
import domain.RoleType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Member_Team_Create {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("AI-PK : 생성 트랜잭션 시작");

    try {
      tx.begin();
      //Team team = new Team();
      Team team = em.find(Team.class,1L);
      //team.setName("teamA");
      //em.persist(team);

      List<Member> members = em.createQuery("select m from Member m", Member.class)
                                .getResultList();
      for (Member member : members){
        member.setTeam(team);
      }


      Member member = new Member();
      member.setUsername("basicMember2");
      member.setRoleType(RoleType.BASIC);
      member.setTeam(team);
      em.persist(member);

      Member memberA=new Member();
      memberA.setUsername("managerMember2");
      memberA.setRoleType(RoleType.MANAGER);
      memberA.setTeam(team);
      em.persist(memberA);

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
