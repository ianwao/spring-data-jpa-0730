package cn.com.taiji.test;

import cn.com.taiji.pojo.Address;
import cn.com.taiji.pojo.People;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_test1");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();

		//添加
//		addPeople(entityManager);
		//更新
//		updatePeople(entityManager);
		//删除
//		delectPeople(entityManager);
		//查询
		selectPeople(entityManager);

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	private static void selectPeople(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
//		List<Address> list = entityManager.createQuery("select a from Address a where a.id = ?1").setParameter(1,1).getResultList();
//		List<People> list = entityManager.createNamedQuery("People.findAll").getResultList();
		List<People> list = entityManager.createNativeQuery("People.findAll").getResultList();
		System.out.println(list.size());
		// 5. 提交事务
		transaction.commit();
	}

	private static void delectPeople(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
//		int count = entityManager.createQuery("delete from People p where p.id=:id").setParameter("id",3).executeUpdate();

		People people = entityManager.find(People.class,4);
		entityManager.remove(people);
		// 5. 提交事务
		transaction.commit();
	}

	private static void updatePeople(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		int count = entityManager.createQuery("update People p set p.name=:name where p.id=:id").setParameter("name","FeiJi").setParameter("id",2).executeUpdate();
		// 5. 提交事务
		transaction.commit();
	}

	private static void addPeople(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Address a = new Address();
		a.setAddress("HongKong");
		People people = new People();
		people.setName("WuJunRu");
		people.setSex("女");
		people.setAddress(a);

		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(a);
		entityManager.persist(people);

		// 5. 提交事务
		transaction.commit();
//		add(entityManager);
//		search(entityManager,"tom");
	}
}
