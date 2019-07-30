package cn.com.taiji.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//居民--》住址 1-1
public class AddressAndPeopleTest {
	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaaa");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();

		addAddress(entityManager);
		addPeople(entityManager);
		deleteAddress(entityManager);
		deletePeople(entityManager);
		updateAddress(entityManager);

		 searchByNamedQuery(entityManager);//使用命名sql查询
		 searchByNativeQuery(entityManager);//使用原生sql查询

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	private static void updateAddress(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Address address = entityManager.find(Address.class, 1);
		address.setAddress("天津");
		// 这里与n-n相比不需保存是因为关联关系是1-1
		// entityManager.persist(address);//需要保存才可以，不然会报错,而且需将写入cascade=CascadeType.ALL给被维护方
		/*
		 * 抛出java.lang.IllegalStateException: org.hibernate.TransientObjectException:
		 * object references an unsaved transient instance - save the transient instance
		 * beforeQuery flushing 因为a是Transient状态，没保存，所以抛异常，只要保存即可
		 */
		// 5. 提交事务
		transaction.commit();

	}

	private static void deletePeople(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Address address = entityManager.find(Address.class, 3);
		entityManager.remove(address);
		// 5. 提交事务
		transaction.commit();

	}

	private static void deleteAddress(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Address address = entityManager.find(Address.class, 2);
		entityManager.remove(address);
		// 5. 提交事务
		transaction.commit();

	}

	private static void searchByNativeQuery(EntityManager entityManager) {

		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		// 支持原生的sql,对应的是数据库表名，列名
		List<People> plist = entityManager.createNativeQuery("select * from people").getResultList();
		
		System.out.println("--------list<People>-------" + plist.size());
		// 5. 提交事务
		transaction.commit();

	}

	private static void searchByNamedQuery(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		// 将sql语句用自定义的方法名代替
		List<People> plist = entityManager.createNamedQuery("People.findAll").getResultList();
		System.out.println("--------list<People>-------" + plist.size());
		// 5. 提交事务
		transaction.commit();

	}

	private static void addAddress(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Address address = new Address();
		People people = new People();
		people.setName("王五");
		people.setSex("男");

		address.setAddress("北京");
		address.setPeople(people);
		entityManager.persist(people);

		entityManager.persist(address);

		// 5. 提交事务
		transaction.commit();

	}

	private static void addPeople(EntityManager entityManager) {

		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		// 方式一必须得在数据库中有数据
		// List<Address> a=entityManager.createQuery("select a from Address
		// a").getResultList();
		// 方式二：必须得在数据库中有数据
		// Address address=entityManager.find(Address.class, 4);
		// 方式三
		Address address = new Address();
		address.setAddress("吉林");
		People p = new People();
		p.setAddress(address);
		p.setName("王六");
		p.setSex("男");
		// address.setPeople(p);
		People peo = entityManager.find(People.class, 9);

		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(address);// 先保存不维护关联关系的一方，即没有外键的一方，线河阳不会多出update语句
		entityManager.persist(p);
		// entityManager.remove(address);
		// entityManager.remove(peo);

		// 5. 提交事务
		transaction.commit();
	}

}
