import domain.Child;
import domain.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CascadeRemoveTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("cascade 트랜잭션 시작");

    try {
      tx.begin();
      Child c1 = em.find(Child.class,1L);
      Child c2 = em.find(Child.class,2L);

      Parent p = em.find(Parent.class,1L);

      em.remove(p);
//      em.persist(child1);
//      em.persist(child2);

      System.out.println("비영속 상태 --------");

      System.out.println("커밋 전 --------");
      tx.commit();
      System.out.println("커밋 후 --------");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      tx.rollback();
    } finally {
      em.close();
      emf.close();
    }
  }
}
