import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Member_Team_fetch_jpql {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("멤버와 팀 페치 : 트랜잭션 시작");

    try {
      tx.begin();

      em.flush();
      em.clear();

      System.out.println("멤버 정보 가져오기 --------");
      List<Member> members = em.createQuery("select m from Member m", Member.class)
          .getResultList();

      System.out.println("멤버와 팀 정보 출력하기 --------");
      System.out.println(members.get(0).getTeam().getName());
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
