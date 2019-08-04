package cn.com.taiji.zuoye2;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
    //一对一
public class AddressAndPeopleTest {
    public AddressAndPeopleTest() {
    }

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaaa");
        EntityManager entityManager = factory.createEntityManager();
        addAddress(entityManager);
        addPeople(entityManager);
        deleteAddress(entityManager);
        deletePeople(entityManager);
        updateAddress(entityManager);
        searchByNamedQuery(entityManager);
        searchByNativeQuery(entityManager);
        entityManager.close();
        factory.close();
    }
         //改
    private static void updateAddress(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Address address = (Address)entityManager.find(Address.class, 1);
        address.setAddress("四川");
        transaction.commit();
    }
        //删
    private static void deletePeople(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Address address = (Address)entityManager.find(Address.class, 2);
        entityManager.remove(address);
        transaction.commit();
    }
       //删
    private static void deleteAddress(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Address address = (Address)entityManager.find(Address.class, 3);
        entityManager.remove(address);
        transaction.commit();
    }
        //查
    private static void searchByNativeQuery(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<People> plist = entityManager.createNativeQuery("select * from people").getResultList();
        System.out.println("--------list<People>-------" + plist.size());
        transaction.commit();
    }
        //查
    private static void searchByNamedQuery(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<People> plist = entityManager.createNamedQuery("People.findAll").getResultList();
        System.out.println("--------list<People>-------" + plist.size());
        transaction.commit();
    }

    private static void addAddress(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Address address = new Address();
        People people = new People();
        people.setName("李思");
        people.setSex("男");
        address.setAddress("上海");
        address.setPeople(people);
        entityManager.persist(people);
        entityManager.persist(address);
        transaction.commit();
    }

    private static void addPeople(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Address address = new Address();
        address.setAddress("山西");
        People p = new People();
        p.setAddress(address);
        p.setName("陈红");
        p.setSex("女");
        People peo = (People)entityManager.find(People.class, 9);
        entityManager.persist(address);
        entityManager.persist(p);
        transaction.commit();
    }
}
