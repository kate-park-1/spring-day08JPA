import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Member_Team_fetch {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("멤버와 팀 페치 : 트랜잭션 시작");

    try {
      tx.begin();
//      Team team = em.find(Team.class, 1L);
      System.out.println("멤버 정보 가져오기 --------");
      Member m1 = em.find(Member.class, 1L);
      Member m2 = em.find(Member.class, 2L);
      Member m3 = em.find(Member.class, 3L);
      Member m4 = em.find(Member.class, 4L);

      System.out.println(m1.getUsername() + "," + m1.getTeam());
      System.out.println(m2.getUsername() + "," + m2.getTeam());
      System.out.println(m3.getUsername() + "," + m3.getTeam());
      System.out.println(m4.getUsername() + "," + m4.getTeam());

      System.out.println("팀 정보 가져오기 --------");
//
//      System.out.println(m2.getTeam());
//      System.out.println(m3.getTeam());
//      System.out.println(m4.getTeam());
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
