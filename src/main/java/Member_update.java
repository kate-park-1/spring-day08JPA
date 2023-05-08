import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Member_update {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("업데이트 트랜잭션 시작");

    try {
      tx.begin();
      Member fMember = em.find(Member.class, 1L);
      System.out.println("영속 상태 수정전--------" + fMember.getUsername());
      fMember.setUsername("basicMember");
      Member fMember2 = em.find(Member.class, 2L);
      fMember2.setUsername("managerMember");

      System.out.println("영속 상태 수정후--------" + fMember.getUsername());
      //em.merge(fMember); em.update() ; em.persist();
      System.out.println("커밋 전 --------");
      tx.commit();
      System.out.println("커밋 후 --------");

      System.out.println("fMember1 == fMember2 ? " + fMember.equals(fMember2));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }
}
