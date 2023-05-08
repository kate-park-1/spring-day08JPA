import domain.Member;
import domain.RoleType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Member_Team_find {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("멤버와 팀 정보 조회 트랜잭션 시작");

    try {
      tx.begin();
      Team team = em.find(Team.class, 1L);

      Member member1 = em.find(Member.class, 1L);
      System.out.println(member1.getUsername() + "," + member1.getRoleType());

      System.out.println("영속 상태 --------");

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
