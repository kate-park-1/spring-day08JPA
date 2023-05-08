import domain.Address;
import domain.Item;
import domain.NewMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MemberTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("Member 트랜잭션 시작");

    try {
      tx.begin();
      Address address = new Address("oldCity","oldStreet","11111");

      NewMember m1 = new NewMember();
      m1.setName("m1");
      m1.setAddress(address);

      Address address1 = new Address("newCity", address.getStreet(), address.getZipcode());
      //address1.setCity("newCity");

      NewMember m2 = new NewMember();
      m2.setName("m2");
      m2.setAddress(address1);


      em.persist(m1);
      em.persist(m2);

      System.out.println("비영속 상태 --------");

//      System.out.println("영속 상태 --------");
//      System.out.println("JPQL 실행 전 --------");
//      List<Parent> parentList = em.createQuery("select p from Parent p", Parent.class).getResultList();
//      System.out.println("p, child 생성 후 --------" + parentList.get(0).getChildList().size());
//
//      em.remove(p);
//      System.out.println("JPQL 실행 전 --------");
//      List<Child> childList = em.createQuery("select c from Child c", Child.class).getResultList();
//      System.out.println("p 삭제 후 --------" + childList.size());


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
