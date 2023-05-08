import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPATest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("트랜잭션 시작");

    try {
      tx.begin();
      Member member = new Member();
      member.setId(2L);
      member.setUsername("memberB");

      Member memberA=new Member();
      memberA.setId(3L);
      memberA.setUsername("memberC");

      System.out.println("비영속 상태 --------");
      em.persist(member);
      em.persist(memberA);
      System.out.println("영속 상태 --------");
//      Member member1 = em.find(Member.class, 1L);
//      Member member2 = em.find(Member.class, 1L);
//      System.out.println("member1 == member2 ? " + member1.equals(member2));
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
