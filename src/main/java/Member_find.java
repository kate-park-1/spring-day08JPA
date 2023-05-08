import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Member_find {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    System.out.println("find transaction 시작");

    try {
      tx.begin();
      System.out.println("member1 find 이전 ==> ");
      Member member1 = em.find(Member.class, 1L);
      System.out.println("member2 find 이전 ==> ");
      Member member2 = em.find(Member.class, 1L);
      System.out.println("member1 == member2 ? " + member1.equals(member2));
      tx.commit();
      System.out.println("커밋 후 ==> ");
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
      emf.close();
    }

  }
}
