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
		// 1. ����EntityManagerFactory
		/*
		 * Persistence�������ڻ�ȡEntityManagerFactoryʵ����
		 * �������һ����ΪcreateEntityManagerFactory�ľ�̬������
		 * EntityManagerFactory�ӿ���Ҫ��������EntityManagerʵ��
		 */
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa1"); // ��Ӧ�־û���Ԫ������

		// 2. ����EntityManager��������hibernate��SessionFactory
		EntityManager entityManager = factory.createEntityManager();

		// 3.��������
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// OneToOne��һ��һ��
		// OneToOneTest.addtest(entityManager);
		// OneToOneTest.addPeople(entityManager);
		// OneToOneTest.addAddress(entityManager);
		// OneToOneTest.searchbyNameQuery(entityManager);
		// OneToOneTest.searchbyNativeQuery(entityManager);
		// OneToOneTest.persist(entityManager);
		// OneToOneTest.find(entityManager);
		// OneToOneTest.remove(entityManager);
		OneToOneTest.update(entityManager);

		// ManyToMany����Զࣩ
		// ManyToManyTest.add(entityManager);
		// ManyToManyTest.select(entityManager);
		// ManyToManyTest.findUser(entityManager);
		// ManyToManyTest.update(entityManager);
		// ManyToManyTest.delete(entityManager);
		// ManyToManyTest.persist(entityManager);
		// ManyToManyTest.find(entityManager);
		// ManyToManyTest.remove(entityManager);
		// ManyToManyTest.update1(entityManager);

		// OneToMany��һ�Զࣩ
		// OneToManyTest.add(entityManager);
		// OneToManyTest.select(entityManager);
//		 OneToManyTest.update(entityManager);
//		 OneToManyTest.delete(entityManager);
		// OneToManyTest.persist(entityManager);
		// OneToManyTest.find(entityManager);
		// OneToManyTest.remove(entityManager);
		// OneToManyTest.update1(entityManager);

		// 5.�ύ����
		transaction.commit();
		// add(entityManager);
		// search(entityManager,"tom");
		// 6. �ر�EntityManager
		entityManager.close();

		// 7. �ر�EntityManagerFactory
		factory.close();
	}

}
