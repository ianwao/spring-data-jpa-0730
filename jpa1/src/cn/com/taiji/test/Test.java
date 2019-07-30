package cn.com.taiji.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import cn.com.taiji.domain.Address;
import cn.com.taiji.domain.Article;
import cn.com.taiji.domain.Customer;
import cn.com.taiji.domain.Employee;
import cn.com.taiji.domain.People;

public class Test {

	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		/*
		 * Persistence类是用于获取EntityManagerFactory实例，
		 * 该类包含一个名为createEntityManagerFactory的静态方法。
		 * EntityManagerFactory接口主要用来创建EntityManager实例
		 */
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa1"); // 对应持久化单元的名字

		// 2. 创建EntityManager，类似于hibernate的SessionFactory
		EntityManager entityManager = factory.createEntityManager();

		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// OneToOne（一对一）
		// OneToOneTest.addtest(entityManager);
		// OneToOneTest.addPeople(entityManager);
		// OneToOneTest.addAddress(entityManager);
		// OneToOneTest.searchbyNameQuery(entityManager);
		// OneToOneTest.searchbyNativeQuery(entityManager);
		// OneToOneTest.persist(entityManager);
		// OneToOneTest.find(entityManager);
		// OneToOneTest.remove(entityManager);
		OneToOneTest.update(entityManager);

		// ManyToMany（多对多）
		// ManyToManyTest.add(entityManager);
		// ManyToManyTest.select(entityManager);
		// ManyToManyTest.findUser(entityManager);
		// ManyToManyTest.update(entityManager);
		// ManyToManyTest.delete(entityManager);
		// ManyToManyTest.persist(entityManager);
		// ManyToManyTest.find(entityManager);
		// ManyToManyTest.remove(entityManager);
		// ManyToManyTest.update1(entityManager);

		// OneToMany（一对多）
		// OneToManyTest.add(entityManager);
		// OneToManyTest.select(entityManager);
//		 OneToManyTest.update(entityManager);
//		 OneToManyTest.delete(entityManager);
		// OneToManyTest.persist(entityManager);
		// OneToManyTest.find(entityManager);
		// OneToManyTest.remove(entityManager);
		// OneToManyTest.update1(entityManager);

		// 5.提交事务
		transaction.commit();
		// add(entityManager);
		// search(entityManager,"tom");
		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

}
