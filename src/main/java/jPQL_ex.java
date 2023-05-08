import domain.*;

import javax.persistence.*;
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
                    + " where m.name like :name";
      List<NewMember> memberList = em.createQuery(jpql, NewMember.class)
          .setParameter("name", "%m%").getResultList();

      for (NewMember m : memberList) {
        System.out.println(m);
      }

      System.out.println("NativeQuery 실행 전 --------");
      String sql = "SELECT * from newmember where name like :name";
      List<NewMember> members1 = em.createNativeQuery(sql, NewMember.class)
          .setParameter("name", "%m%").getResultList();

      System.out.println("findMember" + member.getName());
      System.out.println("JPQL 실행 후 --------" + memberList.size());
      System.out.println("nativeQuery 실행 후 --------" + members1.size());

//      String jpql_address = "SELECT m.address from NewMember m where m.address.getCity() like :city";
//      List<Address> resultList = em.createQuery(jpql_address, Address.class).setParameter("city", "newCity").getResultList();

//      TypedQuery<String> namelist = em.createQuery("select m.name from NewMember m ", String.class);

      List<Team> resultList1 = em.createQuery("select m.team from Member m ", Team.class)
          .setFirstResult(0)
          .getResultList();

      System.out.println("paging result ==> " + resultList1.size());

      List<Team> resultList2 = em.createQuery("select m.team from Member m ", Team.class)
          .setFirstResult(4)
          .getResultList();


      System.out.println("paging result ==> " + resultList1.size());

      //List resultList2 = em.createQuery("select m.name , m.address from NewMember m ").getResultList();
//      TypedQuery<Item> selectIFromItemI = em.createQuery("select i from Item i ", Item.class);

//
//      System.out.println(em.createQuery(jpql));

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
