import domain.Member;
import domain.NewMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jPQL_ex {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("JPQL 트랜잭션 시작");

    try {
      tx.begin();
      NewMember member = em.find(NewMember.class, 1L);
      System.out.println("JPQL 실행 전 --------");
      String jpql = "SELECT m from NewMember m"
                    + "where m.name like '%m%'";
      List<NewMember> members = em.createQuery(jpql, NewMember.class).getResultList();
      System.out.println("findMember" + member.getName());
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
