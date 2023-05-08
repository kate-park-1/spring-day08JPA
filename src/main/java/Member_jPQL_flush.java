import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Member_jPQL_flush {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("flush 트랜잭션 시작");

    try {
      tx.begin();
//      Member m1 = new Member();  m1.setId(21L); m1.setUsername("Member1");
//      Member m2 = new Member();  m2.setId(22L); m2.setUsername("Member2");
//      Member m3 = new Member();  m3.setId(23L); m3.setUsername("Member3");

      System.out.println("비영속 상태 --------");

//      em.persist(m1);
//      em.persist(m2);
//      em.persist(m3);
      System.out.println("영속 상태 --------");
      System.out.println("JPQL 실행 전 --------");
      List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
      System.out.println("JPQL 실행 후 --------" + members.size());
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
