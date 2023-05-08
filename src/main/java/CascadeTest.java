import domain.Child;
import domain.Member;
import domain.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CascadeTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("cascade 트랜잭션 시작");

    try {
      tx.begin();
      Child child1 = new Child();
      child1.setName("child1");

      Child child2 = new Child();
      child2.setName("child2");

      Parent p = new Parent();
      p.setName("parent");
      p.addChild(child1);
      p.addChild(child2);

      em.persist(p);
//      em.persist(child1);
//      em.persist(child2);

      System.out.println("비영속 상태 --------");

      System.out.println("영속 상태 --------");
      System.out.println("JPQL 실행 전 --------");
      List<Parent> parentList = em.createQuery("select p from Parent p", Parent.class).getResultList();
      System.out.println("p, child 생성 후 --------" + parentList.get(0).getChildList().size());

      em.remove(p);

      List<Child> childList = em.createQuery("select c from Child c", Child.class).getResultList();
      System.out.println("p 삭제 후 --------" + childList.size());


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
